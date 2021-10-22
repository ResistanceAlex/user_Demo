package com.demo1.demo1.mapper;

import com.demo1.demo1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="userMapper")
@Mapper
public interface UserMapper {

    //更新用户
    void updateUser(User user);

    //插入用户
    void insertUser(User user);

    //获取所有用户
    List<User> findAllUsers();

    //根据名字获取某个用户
    List<User> findUsers(String name);

    //根据名字获取某个用户
    User findUserById(int id);

    //删除某个用户
    void deleteUser(int id);
}
