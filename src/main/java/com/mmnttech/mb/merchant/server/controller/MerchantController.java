package com.mmnttech.mb.merchant.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmnttech.mb.merchant.server.common.dto.MerchantAuthDto;
import com.mmnttech.mb.merchant.server.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping(value = "/v1/merchants")
public class MerchantController {

	private Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantService merchantService;


    //查询行业分类标准数据
    @RequestMapping(value = "/merchantAuth")
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
    @RequestMapping(value = "/updateMerchantHonestyStatus")
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

    @RequestMapping(method = RequestMethod.POST)
    public RtnMessage authMerchant(@RequestBody MerchantAuthDto merchantAuthDto) {
        RtnMessage rtnMsg = new RtnMessage();
        try {
            merchantService.merchantAuth(merchantAuthDto);
            rtnMsg.setIsSuccess(true);
        } catch (Exception e) {
            logger.error("updateMerchantHonestyStatus 出现异常：", e);
            rtnMsg.setIsSuccess(false);
            rtnMsg.setMessage("商户诚信状态维护异常: 请稍后再试");
        }
        return rtnMsg;
    }


}
