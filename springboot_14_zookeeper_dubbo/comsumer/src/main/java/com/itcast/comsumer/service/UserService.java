package com.itcast.comsumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itcast.provider_ticket.service.TicketService;

import org.springframework.stereotype.Service;

import java.net.PortUnreachableException;

@Service
public class UserService {
    @Reference
    private TicketService ticketService;
    public String userService(){
   return "注册中心的服务："+ticketService.sellTicket();
    }
}
