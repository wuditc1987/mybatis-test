package com.mybatis.demo.service;

import com.mybatis.demo.entity.SysRole;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/11/21 3:30 PM
 */
public interface RoleService {

    SysRole createRole(SysRole role);

    void deleteRole(Long roleId);
    //添加角色-权限之间关系
    void correlationPermissions(Long roleId, Long... permissionIds);
    //移除角色-权限之间关系
    void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
