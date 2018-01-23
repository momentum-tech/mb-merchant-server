package com.mmnttech.mb.merchant.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmnttech.mb.merchant.server.common.dto.MerchantAuthDto;
import com.mmnttech.mb.merchant.server.mapper.MerchantMapper;
import com.mmnttech.mb.merchant.server.model.Merchant;

/**
 * @类名 MerchantService
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午5:24:16
 * @版本 v1.0
 * 
 */

@Transactional
@Service("merchantService")
public class MerchantService {

    protected static final String MerchantAuthStepApprove = "3";

    protected static final String MerchantComStatApprove = "2";

    protected static final String MerchantComStatRefuse = "3";

    @Autowired
    private MerchantMapper merchantMapper;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Merchant approveMerchant(String merchantId) {
        Merchant merchant = merchantMapper.selectByPrimaryKey(merchantId);
        merchant.setAuthStep(MerchantAuthStepApprove);
        merchant.setComStat(MerchantComStatApprove);
        merchantMapper.updateByPrimaryKey(merchant);
        return merchant;
    }

    private Merchant refuseMerchantCert(String merchantId, String reason) {
        Merchant merchant = merchantMapper.selectByPrimaryKey(merchantId);
        merchant.setComStat(MerchantComStatRefuse);
        merchant.setAuthReason(reason);
        merchantMapper.updateByPrimaryKeySelective(merchant);
        return merchant;
    }

    public Merchant merchantAuth(MerchantAuthDto merchantAuthDto) {
        if (merchantAuthDto.isApprove()) {
            return approveMerchant(merchantAuthDto.getMerchantId());
        } else {
            return refuseMerchantCert(merchantAuthDto.getMerchantId(), merchantAuthDto.getReason());
        }
    }
    


	public Map<String, Object> queryMerchantInfo(String recId) {
		List<Object> paramLst = new ArrayList<Object>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t2.*, CC.category FROM (");
		sql.append("SELECT t1.*, AC.area FROM (");
		sql.append("SELECT MCT.*, IDC.industry FROM (SELECT * FROM t_merchant WHERE rec_id = ?) MCT LEFT JOIN (SELECT * FROM t_industry_code)IDC ON MCT.industry_code = IDC.industry_code");
		sql.append(")t1 LEFT JOIN (SELECT * FROM t_area_code) AC ON t1.area_code = AC.area_code");
		sql.append(")t2 LEFT JOIN (SELECT * FROM t_category_code) CC ON t2.category_code = CC.category_code");
		
		paramLst.add(recId);
		
		return jdbcTemplate.queryForMap(sql.toString(), paramLst.toArray());
	}
}
