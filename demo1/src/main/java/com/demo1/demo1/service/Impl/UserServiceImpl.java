package com.demo1.demo1.service.Impl;

import com.demo1.demo1.entity.User;
import com.demo1.demo1.mapper.UserMapper;
import com.demo1.demo1.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public PageInfo<User> findAllUsers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = mapper.findAllUsers();
        PageInfo<User> page = new PageInfo(users);
        return page;
    }


    @Override
    public PageInfo<User> findUsers(String name,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = mapper.findUsers(name);
        PageInfo<User> page = new PageInfo(users);
        return page;
    }

    @Override
    public User findUserById(int id) {
        return mapper.findUserById(id);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public void insertUser(User user) {
        mapper.insertUser(user);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public void deleteUser(int id) {
        mapper.deleteUser(id);
    }


}
