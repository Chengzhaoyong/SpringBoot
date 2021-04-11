package com.kuang.helloshiro.service;

import com.kuang.helloshiro.mapper.UserMapper;
import com.kuang.helloshiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }
}
