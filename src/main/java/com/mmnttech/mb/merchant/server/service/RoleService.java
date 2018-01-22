package com.mmnttech.mb.merchant.server.service;

import com.mmnttech.mb.merchant.server.mapper.RoleMapper;
import com.mmnttech.mb.merchant.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Stelo
 * @createAt 2018/1/19
 **/
@Service("roleService")
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserService userService;

    public Role queryRoleByRoleId(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public Role queryRoleByUserId(String userId) {
        return queryRoleByRoleId(userService.queryRoleIdByUserId(userId));
    }
}
