package com.demo1.demo1.service;

import com.demo1.demo1.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    //更新用户
    void updateUser(User user);

    //插入用户
    void insertUser(User user);

    //获取所有用户
    PageInfo<User> findAllUsers(Integer pageNum, Integer pageSize);

    //根据名字获取某个用户
    PageInfo<User> findUsers(String name,Integer pageNum, Integer pageSize);

    //根据名字获取某个用户
    User findUserById(int id);

    //删除某个用户
    void deleteUser(int id);
}
