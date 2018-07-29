package com.wcc.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author wangcc
 * @decription:
 * @date 2018/7/14 20:02
 */
public class AppProducer {
    private static final String url = "tcp://localhost:61616";
    private static final String topicName = "topic-test";
    public static void main(String[] args) throws Exception{
        //1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2、创建Connection
        Connection connection = connectionFactory.createConnection();

        //3、启动连接
        connection.start();

        //4、创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5、创建一个目标队列
        Destination destination = session.createTopic(topicName);

        //6、创建一个生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            //7、创建消息
            TextMessage textMessage = session.createTextMessage("test"+i);
            //8、发布消息到目标
            producer.send(textMessage);

            System.out.println("发送消息"+textMessage.getText());
        }

        //9、关闭连接
        connection.close();

    }
}
