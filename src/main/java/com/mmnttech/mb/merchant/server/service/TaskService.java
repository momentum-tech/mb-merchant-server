package com.mmnttech.mb.merchant.server.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.mmnttech.mb.merchant.server.common.entity.DictionaryConst;
import com.mmnttech.mb.merchant.server.common.entity.QueryEntity;
import com.mmnttech.mb.merchant.server.mapper.TaskMapper;
import com.mmnttech.mb.merchant.server.model.Task;
import com.mmnttech.mb.merchant.server.service.common.RoleService;
import com.mmnttech.mb.merchant.server.util.StringUtil;

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
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    
    public void createTask(Task task) {
    	task.setRecId(StringUtil.getUUID());
    	task.setCreateDate(new Date());
    	task.setStatus(DictionaryConst.TTask.STATUS_STANDBY);
    	
    	taskMapper.insert(task);
    }

	public List<Map<String, Object>> queryTaskInfoLst(QueryEntity queryEntity) {
		List<Object> paramLst = new ArrayList<Object>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_task WHERE status = ? and operator = ?");
		paramLst.add(DictionaryConst.TTask.STATUS_STANDBY);
		paramLst.add(queryEntity.getUserId());

		int offset = (queryEntity.getPage() - 1) * queryEntity.getRows();
		
		sql.append(" ORDER BY create_date desc LIMIT ?, ?");
		paramLst.add(offset);
		paramLst.add(queryEntity.getRows());
		
		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql.toString(), paramLst.toArray());
		
		if(records != null && !records.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			for(Map<String, Object> record : records) {
				record.put("create_date", format.format(record.get("create_date")));
			}
		}
		return records;
	}
    
    
    
}
