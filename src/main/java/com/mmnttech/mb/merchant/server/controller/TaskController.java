package com.mmnttech.mb.merchant.server.controller;

import com.mmnttech.mb.merchant.server.common.entity.RtnMessage;
import com.mmnttech.mb.merchant.server.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stelo
 * @createAt 2018/1/18
 **/
@RestController
@RequestMapping(value = "/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @RequestMapping(value = "/wait/count", method = RequestMethod.GET)
    public RtnMessage getWaitProcessTaskCount(@RequestParam String userId) {
        RtnMessage rtnMsg = new RtnMessage();
        try {
            rtnMsg.setIsSuccess(true);
            rtnMsg.setRtnObj(taskService.countWaitProcessTaskByUserId(userId));
        } catch (Exception e) {
            logger.error("getTaskCount 出现异常：", e);
            rtnMsg.setIsSuccess(false);
            rtnMsg.setMessage("获取待领取任务计数失败：请稍后再试");
        }

        return rtnMsg;
    }

    @RequestMapping(value = "/wait", method = RequestMethod.GET)
    public RtnMessage getWaitProcessTask(@RequestParam String userId, @RequestParam int page, @RequestParam int size) {
        RtnMessage rtnMsg = new RtnMessage();
        try {
            rtnMsg.setIsSuccess(true);
            rtnMsg.setRtnObj(taskService.queryWaitProcessTaskByUserId(userId, page, size));
        } catch (Exception e) {
            logger.error("getTask 出现异常：", e);
            rtnMsg.setIsSuccess(false);
            rtnMsg.setMessage("获取待领取任务失败：请稍后再试");
        }
        return rtnMsg;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public RtnMessage getTaskCount(@RequestParam String userId) {
        RtnMessage rtnMsg = new RtnMessage();
        try {
            rtnMsg.setIsSuccess(true);
            rtnMsg.setRtnObj(taskService.countTaskByUserId(userId));
        } catch (Exception e) {
            logger.error("getTaskCount 出现异常：", e);
            rtnMsg.setIsSuccess(false);
            rtnMsg.setMessage("获取待领取任务计数失败：请稍后再试");
        }

        return rtnMsg;
    }

    @RequestMapping(method = RequestMethod.GET)
    public RtnMessage getTask(@RequestParam String userId, @RequestParam int page, @RequestParam int size) {
        RtnMessage rtnMsg = new RtnMessage();
        try {
            rtnMsg.setIsSuccess(true);
            rtnMsg.setRtnObj(taskService.queryTaskByUserId(userId, page, size));
        } catch (Exception e) {
            logger.error("getTask 出现异常：", e);
            rtnMsg.setIsSuccess(false);
            rtnMsg.setMessage("获取待领取任务失败：请稍后再试");
        }
        return rtnMsg;
    }

    @RequestMapping(method = RequestMethod.POST)
    public RtnMessage claimTask(@RequestParam String userId, @RequestParam String taskId) {
        RtnMessage rtnMsg = new RtnMessage();
        try {
            rtnMsg.setIsSuccess(true);
            rtnMsg.setRtnObj(taskService.claimTask(userId, taskId));
        } catch (Exception e) {
            logger.error("getTask 出现异常：", e);
            rtnMsg.setIsSuccess(false);
            rtnMsg.setMessage("获取待领取任务失败：请稍后再试");
        }
        return rtnMsg;
    }
}
