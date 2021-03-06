package com.mmnttech.mb.merchant.server.service.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mmnttech.mb.merchant.server.mapper.MenuGroupMapper;
import com.mmnttech.mb.merchant.server.mapper.MenuMapper;
import com.mmnttech.mb.merchant.server.mapper.RoleMenuGroupMapper;
import com.mmnttech.mb.merchant.server.model.MenuGroup;
import com.mmnttech.mb.merchant.server.model.RoleMenuGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mmnttech.mb.merchant.server.common.entity.QueryEntity;
import com.mmnttech.mb.merchant.server.common.entity.RtnMessage;
import com.mmnttech.mb.merchant.server.util.StringUtil;
import com.mmnttech.mb.merchant.server.util.Validator;
import tk.mybatis.mapper.entity.Example;

/**
 * @类名 MenuGroupService
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午9:09:46
 * @版本 v1.0
 * 
 */
@Service("menuGroupService")
public class MenuGroupService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MenuGroupMapper menuGroupMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMenuGroupMapper roleMenuGroupMapper;
	
	public List<Map<String, Object>> queryMenuGroupLst(QueryEntity queryEntity) {
		List<Object> paramLst = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		
		if(Validator.isNotBlank(queryEntity.getRoleId())) {
			sql.append("SELECT mg.* FROM ((SELECT * FROM t_role_menu_group WHERE role_id = ?) rmp LEFT JOIN t_menu_group mg ON rmp.menu_group_id = mg.rec_id)");
			paramLst.add(queryEntity.getRoleId());
		} else {
			sql.append("SELECT * FROM t_menu_group mg WHERE 1 = 1");
			
			if(Validator.isNotBlank(queryEntity.getQueryName())) {
				sql.append(" AND name = ?");
				paramLst.add(queryEntity.getQueryName());
			}
		}
		
		int offset = (queryEntity.getPage() - 1) * queryEntity.getRows();
		
		sql.append(" ORDER BY mg.sequence LIMIT ?, ?");
		paramLst.add(offset);
		paramLst.add(queryEntity.getRows());
		return jdbcTemplate.queryForList(sql.toString(), paramLst.toArray());
	}
	
	public RtnMessage createMenuGroup(MenuGroup menuGroup) {
		RtnMessage rtnMsg = new RtnMessage();

        Example example = new Example(MenuGroup.class);
        example.createCriteria().andEqualTo("name", menuGroup.getName());
        if (menuGroupMapper.selectCountByExample(example) == 0) {
            menuGroup.setRecId(StringUtil.getUUID());
			menuGroup.setCreateDate(new Date());
			menuGroup.setSequence(100);
			
			menuGroupMapper.insert(menuGroup);
		} else {
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_CREATE_2);
		}
		return rtnMsg;
	}

	public void updateMenuGroup(MenuGroup menuGroup) {
		menuGroupMapper.updateByPrimaryKeySelective(menuGroup);
	}
	
	public RtnMessage deleteMenuGroup(MenuGroup menuGroup) {
		RtnMessage rtnMsg = new RtnMessage();

        Example example = new Example(MenuGroup.class);
        example.createCriteria().andGreaterThan("recId", menuGroup.getRecId());
        if (menuMapper.selectCountByExample(example) == 0) {
            Example roleMenuGroupExample = new Example(RoleMenuGroup.class);
            roleMenuGroupExample.createCriteria().andEqualTo("recId", menuGroup.getRecId());

            if (roleMenuGroupMapper.selectCountByExample(roleMenuGroupExample) == 0) {
                menuGroupMapper.deleteByPrimaryKey(menuGroup.getRecId());
			} else {
				rtnMsg.setIsSuccess(false);
				rtnMsg.setMessage(RtnMessage.ERROR_DELETE_3 + "(请先删除关联菜单数据)");
			}
		} else {
			rtnMsg.setIsSuccess(false);
			rtnMsg.setMessage(RtnMessage.ERROR_DELETE_3 + "(请先删除关联角色数据)");
		}
		return rtnMsg;
	}

	public List<String> queryMenuGroupIdByRoleId(String roleId) {
		List<String> menuGroupIdLst = new ArrayList<String>();
		
		List<Object> paramLst = new ArrayList<Object>();
		
		String sql = "SELECT mg.rec_id FROM ((SELECT * FROM t_role_menu_group WHERE role_id = ?) rmp LEFT JOIN t_menu_group mg ON rmp.menu_group_id = mg.rec_id) ORDER BY mg.sequence";
		paramLst.add(roleId);
		
		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql.toString(), paramLst.toArray());
		for(Map<String, Object> record : records) {
			menuGroupIdLst.add(String.valueOf(record.get("rec_id")));
		}
		
		return menuGroupIdLst;
	}

	@Transactional
	public void txBatchUpdateMenuGroupSeq(List<String> menuGroupIdLst) {
		for(int i = 0; i < menuGroupIdLst.size(); i++) {
			String menuGroupId = menuGroupIdLst.get(i);
			
			MenuGroup menuGroup = new MenuGroup();
			menuGroup.setRecId(menuGroupId);
			menuGroup.setSequence(i + 1);
			
			menuGroupMapper.updateByPrimaryKeySelective(menuGroup);
		}
	}
	
	
	
	
	
	
	
}
