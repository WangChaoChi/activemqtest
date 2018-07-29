package com.wcc.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author wangcc
 * @decription:
 * @date 2018/7/14 20:02
 */
public class AppConsumer {
//    private static final String url = "failover(tcp://localhost:61616,tcp://localhost:61617,tcp://localhost:61618)?randomize=true";
    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "queue-test";
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
        Destination destination = session.createQueue(queueName);

        //6、创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7、创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8、关闭连接
        //connection.close();

    }
}
