package com.mybatis.demo.service.impl;

import com.mybatis.demo.entity.SysRole;
import com.mybatis.demo.service.RoleService;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/11/21 3:31 PM
 */
public class RoleServiceImpl implements RoleService {

    @Override
    public SysRole createRole(SysRole role) {
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {

    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {

    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {

    }
}
