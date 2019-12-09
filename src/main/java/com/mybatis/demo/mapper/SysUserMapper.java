package com.mybatis.demo.mapper;

import com.mybatis.demo.entity.SysUser;
import com.mybatis.demo.entity.SysUserExample;
import java.util.List;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer id);

    SysUser findByLoginname(String loginName);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> findAll();
}