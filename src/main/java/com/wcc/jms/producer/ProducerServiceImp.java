package com.wcc.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author wangcc
 * @decription:
 * @date 2018/7/15 13:59
 */
public class ProducerServiceImp implements ProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

//    @Resource(name = "queueDestination")//队列模式
    @Resource(name = "topicDestination")//主题模式
    Destination destination;

    public void sendMessage(final String message) {
        //使用jmsTemplate发送消息
        /*jmsTemplate.send(destination, new MessageCreator() {
            //创建消息
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage =session.createTextMessage(message);
                return textMessage;
            }
        });*/

        jmsTemplate.send(destination,
                session -> session.createTextMessage(message));

        System.out.println("发送消息："+message);
    }
}
