package com.itcast.provider_ticket.service;


//import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String sellTicket() {
        return "《厉害了我的国》";
    }
}
