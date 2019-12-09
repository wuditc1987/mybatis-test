package com.mybatis.demo.service;

import com.mybatis.demo.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/9/29 3:18 PM
 */
public interface UserService {

    SysUser findById(Integer id);

    List<SysUser> findAll();

    SysUser findByLoginName(String loginName);

    SysUser findByPhone(String phone);

    SysUser findByEmail(String email);

    void updateById(Integer id,SysUser user);

    void save(SysUser user);

    Set<String> findRoles(Integer id);

    Set<String> findRoles(String username);

//    Lis

    void changePassword(Integer userId,String newPassword);
}
