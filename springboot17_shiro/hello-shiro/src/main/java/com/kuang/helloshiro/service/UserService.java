package com.kuang.helloshiro.service;

import com.kuang.helloshiro.pojo.User;

public interface UserService {
    User queryByName(String name);
}
