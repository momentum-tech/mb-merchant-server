package com.mmnttech.mb.merchant.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.mmnttech.mb.merchant.server.mapper.TaskMapper;
import com.mmnttech.mb.merchant.server.model.Task;
import com.mmnttech.mb.merchant.server.service.common.RoleService;

/**
 * @author Stelo
 * @createAt 2018/1/18
 **/
@Service("taskService")
public class TaskService {

    protected static final String TaskStatusProcessing = "P";

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private RoleService roleService;

    public Task claimTask(String userId, String taskId) {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        task.setOperator(userId);
        task.setStatus(TaskStatusProcessing);
        taskMapper.updateByPrimaryKey(task);
        return task;
    }

    public int countWaitProcessTaskByUserId(String userId) {
        Example example = new Example(Task.class);
        example.createCriteria()
                .andEqualTo("roleId", roleService.queryRoleByUserId(userId).getRecId())
                .andIsNull("operator");
        return taskMapper.selectCountByExample(example);
    }

    public List<Task> queryWaitProcessTaskByUserId(String userId, int page, int size) {
        Example example = new Example(Task.class);
        example.createCriteria()
                .andEqualTo("roleId", roleService.queryRoleByUserId(userId).getRecId())
                .andIsNull("operator");
        return PageHelper.startPage(page, size).doSelectPage(() -> taskMapper.selectByExample(example));
    }

    public int countTaskByUserId(String userId) {
        Task task = new Task();
        task.setOperator(userId);
        return taskMapper.selectCount(task);
    }

    public List<Task> queryTaskByUserId(String userId, int page, int size) {
        Task task = new Task();
        task.setOperator(userId);
        return PageHelper.startPage(page, size).doSelectPage(() -> taskMapper.select(task));
    }
}
