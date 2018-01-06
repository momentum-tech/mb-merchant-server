package com.mmnttech.mb.merchant.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmnttech.mb.merchant.server.common.entity.QueryEntity;
import com.mmnttech.mb.merchant.server.common.entity.RtnMessage;

/**
 * @类名 UserController
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午5:24:09
 * @版本 v1.0
 * 
 */

@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//商户登录
	@ResponseBody
	@RequestMapping(value = "login")
	public RtnMessage login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		try {
			//TODO
			
		} catch (Exception e) {
			logger.error("login 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_LOGIN_1);
		}
		
		return rtnMsg;
	}
	

	//商户注册
	@ResponseBody
	@RequestMapping(value = "register")
	public RtnMessage register(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		try {
			//TODO
			
		} catch (Exception e) {
			logger.error("register 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage("注册异常：请稍后再试");
		}
		
		return rtnMsg;
	}
	
	//商户密码修改
	@ResponseBody
	@RequestMapping(value = "passwd")
	public RtnMessage passwd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
			//TODO
			
		} catch (Exception e) {
			logger.error("passwd 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage("修改密码异常：请稍后再试");
		}
		
		return rtnMsg;
	}
}
