package com.kuang.helloshiro.mapper;

import com.kuang.helloshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User queryByName(String name);
}
