package com.mmnttech.mb.merchant.server.service;

import com.mmnttech.mb.merchant.server.common.dto.MerchantAuthDto;
import com.mmnttech.mb.merchant.server.mapper.MerchantMapper;
import com.mmnttech.mb.merchant.server.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        merchantMapper.updateByPrimaryKey(merchant);
        return merchant;
    }

    public Merchant merchantAuth(MerchantAuthDto merchantAuthDto) {
        if (merchantAuthDto.isApprove()) {
            return approveMerchant(merchantAuthDto.getMerchantId());
        } else {
            return refuseMerchantCert(merchantAuthDto.getMerchantId(), merchantAuthDto.getReason());
        }
    }
}
