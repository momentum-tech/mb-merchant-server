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
 * @类名 MerchantController
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午5:24:02
 * @版本 v1.0
 * 
 */
@Controller
public class MerchantController {

	private Logger logger = LoggerFactory.getLogger(MerchantController.class);
	

	//查询行业分类标准数据
	@ResponseBody
	@RequestMapping(value = "merchantAuth")
	public RtnMessage merchantAuth(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("merchantAuth 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage("商户认证异常: 请稍后再试");
		}
		return rtnMsg;
	}
	

	//商户诚信状态维护
	@ResponseBody
	@RequestMapping(value = "updateMerchantHonestyStatus")
	public RtnMessage updateMerchantHonestyStatus(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("updateMerchantHonestyStatus 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage("商户诚信状态维护异常: 请稍后再试");
		}
		return rtnMsg;
	}
	
	
}
