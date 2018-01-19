package com.mmnttech.mb.merchant.server.service;

import com.mmnttech.mb.merchant.server.mapper.SvcUserMapper;
import com.mmnttech.mb.merchant.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类名 UserService
 * @描述:
 *   TODO
 * @版权: Copyright (c) 2017 云南动量科技有限公司
 * @创建人 James
 * @创建时间 2018年1月6日 下午5:24:23
 * @版本 v1.0
 * 
 */

@Service("userService")
public class UserService {
    @Autowired
    private SvcUserMapper svcUserMapper;

    @Autowired
    private RoleService roleService;

    public String queryRoleIdByUserId(String userId) {
        return svcUserMapper.selectByPrimaryKey(userId).getRoleId();
    }
}
