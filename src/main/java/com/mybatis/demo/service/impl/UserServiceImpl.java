package com.mybatis.demo.service.impl;

import com.mybatis.demo.common.base.utils.PasswordHelper;
import com.mybatis.demo.mapper.SysRoleMapper;
import com.mybatis.demo.mapper.SysUserMapper;
import com.mybatis.demo.entity.SysUser;
import com.mybatis.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/9/29 3:19 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    private PasswordHelper helper = new PasswordHelper();

    @Override
    public SysUser findById(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(Integer id,SysUser user){
        if(null != user){
            user.setId(id);
        }
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public SysUser findByLoginName(String loginName) {
        return sysUserMapper.findByLoginname(loginName);
    }

    @Override
    public SysUser findByPhone(String phone) {
        return null;
    }

    @Override
    public SysUser findByEmail(String email) {
        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public void save(SysUser user) {
        helper.encryptPassword(user);
        sysUserMapper.insert(user);
    }

    @Override
    public Set<String> findRoles(Integer id) {
        return new HashSet<String>(sysRoleMapper.findRoleListByUserId(id));
    }

    @Override
    public Set<String> findRoles(String username) {
        return new HashSet<String>(sysRoleMapper.findRoleListByUsername(username));
    }

    @Override
    public void changePassword(Integer userId, String newPassword) {
        SysUser user = findById(userId);
        user.setPassword(newPassword);
        helper.encryptPassword(user);
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

}
