package com.bluesky.tech.activemq.spring;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try{
            System.out.println("listener:"+msg.getText());
        }catch (Exception ex){
            System.out.println("ex:"+ex);
        }
    }
}
