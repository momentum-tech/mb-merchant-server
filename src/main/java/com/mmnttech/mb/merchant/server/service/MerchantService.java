package com.mmnttech.mb.merchant.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mmnttech.mb.merchant.server.database.entity.Merchant;
import com.mmnttech.mb.merchant.server.util.Validator;

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

@Service("merchantService")
public class MerchantService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> queryMerchantInfoLst(Merchant merchant, int page, int rows) {
		List<Object> paramLst = new ArrayList<Object>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_merchant WHERE 1 = 1");
		
		if(Validator.isNotBlank(merchant.getCpyName())) {
			sql.append(" AND cpy_name like ?");
			paramLst.add("%" + merchant.getCpyName() + "%");
		}
		if(Validator.isNotBlank(merchant.getLinkman())) {
			sql.append(" AND linkman = ?");
			paramLst.add(merchant.getLinkman());
		}
		if(Validator.isNotBlank(merchant.getComStat())) {
			sql.append(" AND com_stat = ?");
			paramLst.add(merchant.getComStat());
		}
		
		int offset = (page - 1) * rows;
		
		sql.append(" ORDER BY create_date desc LIMIT ?, ?");
		paramLst.add(offset);
		paramLst.add(rows);
		
		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql.toString(), paramLst.toArray());
//		if(records != null && !records.isEmpty()) {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			for(Map<String, Object> record : records) {
//				record.put("create_date", record.get("create_date") == null?"":format.format(record.get("create_date")));
//			}
//		}
		return records;
	}
	
	
}
