package com.example.itcastspringbootstartautoconfigurer.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class HelloService {

    HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String getHello(){
      return helloProperties.getPrifix()+"-name-"+helloProperties.getSuffix();
  }
}
