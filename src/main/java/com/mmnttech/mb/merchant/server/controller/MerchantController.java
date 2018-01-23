package com.mmnttech.mb.merchant.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmnttech.mb.merchant.server.common.dto.MerchantAuthDto;
import com.mmnttech.mb.merchant.server.common.entity.DictionaryConst;
import com.mmnttech.mb.merchant.server.common.entity.QueryEntity;
import com.mmnttech.mb.merchant.server.common.entity.RtnMessage;
import com.mmnttech.mb.merchant.server.model.Attach;
import com.mmnttech.mb.merchant.server.model.Merchant;
import com.mmnttech.mb.merchant.server.service.AttachService;
import com.mmnttech.mb.merchant.server.service.MerchantService;
import com.mmnttech.mb.merchant.server.service.common.StaticFileService;

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
    
    @Autowired
    private AttachService attachService;


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
    

	//获取商户详细信息
    @RequestMapping(value = "/queryMerchantInfo")
    public RtnMessage queryMerchantInfo(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("merchant") Merchant merchant) {
		RtnMessage rtnMsg = new RtnMessage();
		try {
			Map<String, Object> merchantInfo = merchantService.queryMerchantInfo(merchant.getRecId());
			List<Attach> attachLst = attachService.findByMasterId(merchant.getRecId());
			for(Attach attach : attachLst) {
				if(attach.getType().equals(DictionaryConst.TAttach.TYPE_ID_FRONT_IMAGE)) {
					merchantInfo.put("idCardFrontUrl", StaticFileService.STATIC_IMAGE_PATH + attach.getAttachUrl());
				} else if(attach.getType().equals(DictionaryConst.TAttach.TYPE_ID_BACK_IMAGE)) {
					merchantInfo.put("idCardBackUrl", StaticFileService.STATIC_IMAGE_PATH + attach.getAttachUrl());
				} else if(attach.getType().equals(DictionaryConst.TAttach.TYPE_MERCHANT_LICENSE_IMAGE)) {
					merchantInfo.put("licenseNoUrl", StaticFileService.STATIC_IMAGE_PATH + attach.getAttachUrl());
				}
			}
			rtnMsg.setRtnObj(merchantInfo);
		} catch (Exception e) {
			logger.error("queryMerchantInfo 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_QUERY_1);
		}
		
		return rtnMsg;
	}



	//获取商户详细信息
    @RequestMapping(value = "/updateMerchantStatus")
    public RtnMessage updateMerchantStatus(HttpServletRequest request, HttpServletResponse response,
    		@ModelAttribute("merchant") Merchant merchant) {
		RtnMessage rtnMsg = new RtnMessage();
		try {
			String taskId = request.getParameter("taskId");
			if(taskId!= null) {
				merchantService.txUpdateMerchantStatus(merchant, taskId);
			}
		} catch (Exception e) {
			logger.error("queryMerchantInfo 出现异常：", e);
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_QUERY_1);
		}
		
		return rtnMsg;
	}

}
