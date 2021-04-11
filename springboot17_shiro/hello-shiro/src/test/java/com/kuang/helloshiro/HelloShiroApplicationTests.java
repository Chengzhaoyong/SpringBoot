package com.kuang.helloshiro;

import com.kuang.helloshiro.pojo.User;
import com.kuang.helloshiro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloShiroApplicationTests {
@Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.queryByName("zhangsan");
        System.out.println(user);
    }

}
