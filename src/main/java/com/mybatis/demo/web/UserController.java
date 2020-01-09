package com.mybatis.demo.web;

import com.mybatis.demo.entity.SysUser;
import com.mybatis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wudi
 * @version 1.0
 * @description TODO
 * @date 2019/9/29 3:18 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

//    @RequiresPermissions(":")
    @RequestMapping(value = "/one")
    public SysUser findOne(@RequestParam Integer id){
        SysUser user = userService.findById(id);
        logger.info("user = [{}]",user);
        return user;
    }

    @RequestMapping(value = "/{id}")
    public void update(@PathVariable Integer id, SysUser user){
        userService.updateById(id,user);
    }

}
