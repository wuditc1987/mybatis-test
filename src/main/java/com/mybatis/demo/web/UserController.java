package com.mybatis.demo.web;

import com.mybatis.demo.entity.SysUser;
import com.mybatis.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wudi
 * @version 1.0
 * @Description TODO
 * @date 2019/9/29 3:18 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

//    @RequiresPermissions(":")
    @RequestMapping(value = "/one")
    public SysUser findOne(@RequestParam Integer id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/{id}")
    public void update(@PathVariable Integer id, SysUser user){
        userService.updateById(id,user);
    }

}
