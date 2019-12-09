package com.mybatis.demo.mapper;

import com.mybatis.demo.entity.SysRole;
import com.mybatis.demo.entity.SysRoleExample;
import java.util.List;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<String> findRoleListByUserId(Integer userId);

    List<String> findRoleListByUsername(String username);
}