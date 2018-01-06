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
 * @类名 StandardDataController
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午5:24:05
 * @版本 v1.0
 * 
 */

@Controller
public class StandardDataController {

	private Logger logger = LoggerFactory.getLogger(StandardDataController.class);
	

	//查询行业分类标准数据
	@ResponseBody
	@RequestMapping(value = "queryTradeClassificationLst")
	public RtnMessage queryMenuGroupLst(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("queryTradeClassificationLst 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_QUERY_1);
		}
		return rtnMsg;
	}
	

	//创建行业标准数据
	@ResponseBody
	@RequestMapping(value = "createTradeClassification")
	public RtnMessage createTradeClassification(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("createTradeClassification 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_CREATE_1);
		}
		return rtnMsg;
	}
	

	//删除行业标准数据
	@ResponseBody
	@RequestMapping(value = "deleteTradeClassification")
	public RtnMessage deleteTradeClassification(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("deleteTradeClassification 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_DELETE_1);
		}
		return rtnMsg;
	}
	

	//修改行业标准数据
	@ResponseBody
	@RequestMapping(value = "updateTradeClassification")
	public RtnMessage updateTradeClassification(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("updateTradeClassification 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_UPDATE_1);
		}
		return rtnMsg;
	}
	

	//查询区域标准数据管理
	@ResponseBody
	@RequestMapping(value = "queryAreaDataLst")
	public RtnMessage queryAreaDataLst(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("queryAreaDataLst 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_QUERY_1);
		}
		return rtnMsg;
	}

	//创建区域标准数据管理
	@ResponseBody
	@RequestMapping(value = "createAreaData")
	public RtnMessage createAreaData(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("createAreaData 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_CREATE_1);
		}
		return rtnMsg;
	}
	

	//删除区域标准数据管理
	@ResponseBody
	@RequestMapping(value = "deleteAreaData")
	public RtnMessage deleteAreaData(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("deleteAreaData 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_DELETE_1);
		}
		return rtnMsg;
	}
	

	//修改区域标准数据管理
	@ResponseBody
	@RequestMapping(value = "updateAreaData")
	public RtnMessage updateAreaData(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("queryEntity") QueryEntity queryEntity) {
		RtnMessage rtnMsg = new RtnMessage();
		
		try {
		} catch (Exception e) {
			logger.error("updateAreaData 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_UPDATE_1);
		}
		return rtnMsg;
	}
}
