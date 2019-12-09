package com.mybatis.demo.mapper;

import com.mybatis.demo.entity.SysMenu;
import com.mybatis.demo.entity.SysMenuExample;
import java.util.List;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}