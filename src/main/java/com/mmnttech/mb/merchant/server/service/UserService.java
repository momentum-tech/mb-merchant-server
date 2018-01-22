package com.mmnttech.mb.merchant.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmnttech.mb.merchant.server.common.entity.RtnMessage;
import com.mmnttech.mb.merchant.server.database.entity.SvcUser;
import com.mmnttech.mb.merchant.server.database.entity.SvcUserExample;
import com.mmnttech.mb.merchant.server.database.mappers.SvcUserMapper;
import com.mmnttech.mb.merchant.server.util.StringUtil;

/**
 * @类名 UserService
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午5:24:23
 * @版本 v1.0
 * 
 */

@Service("userService")
public class UserService {
	
	@Autowired
	private SvcUserMapper svcUserMapper;

	public RtnMessage doLogin(SvcUser svcUser, List<String> roleIdLst) {
		RtnMessage rtnMsg = new RtnMessage();

		SvcUserExample example = new SvcUserExample();
		example.createCriteria().andUserTelEqualTo(svcUser.getUserTel())
			.andUserPwdEqualTo(StringUtil.MD5(svcUser.getUserPwd())).andRoleIdIn(roleIdLst);
		
		List<SvcUser> records = svcUserMapper.selectByExample(example);
		if(records != null && !records.isEmpty()) {
			if(records.size() == 1) {
				SvcUser record = records.get(0);
				rtnMsg.setRtnObj(record);
			} else {
				rtnMsg.setIsSuccess(false);
				rtnMsg.setMessage(RtnMessage.ERROR_LOGIN_1);
			}
		} else {
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_LOGIN_1);
		}
		return rtnMsg;
	}

	
}
