package com.demo1.demo1.controller;

import com.demo1.demo1.entity.User;
import com.demo1.demo1.service.Impl.UserServiceImpl;
import com.demo1.demo1.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    //打印日志
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserService userService;

    @GetMapping(value = "/allUsers/{pageNum}/{pageSize}")
    public PageInfo findAllUsers(@PathVariable(value="pageNum") Integer pageNum,
                                 @PathVariable(value="pageSize") Integer pageSize){
        PageInfo<User> allUsers = userService.findAllUsers(pageNum, pageSize);
        return allUsers;
    }

    @GetMapping(value = "/findUsers/{name}/{pageNum}/{pageSize}")
    public PageInfo findUserByName(@PathVariable(value="name") String name,
                                   @PathVariable(value="pageNum") Integer pageNum,
                                   @PathVariable(value="pageSize") Integer pageSize){
        PageInfo<User> users = userService.findUsers(name,pageNum, pageSize);
        return users;
    }

    @GetMapping(value = "/findUserById/{id}")
    public User findAllUsers(@PathVariable(value="id") int id){
        User user = userService.findUserById(id);
        return user;
    }

    @PostMapping(value = "/insert")
    public void insertUser(@RequestBody User user){
        userService.insertUser(user);
    }

    @PostMapping(value = "/update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @GetMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable(value="id") int id){
        userService.deleteUser(id);
    }

}
