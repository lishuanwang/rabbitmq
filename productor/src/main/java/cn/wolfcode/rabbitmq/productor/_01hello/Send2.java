package cn.wolfcode.rabbitmq.productor._01hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.tomcat.util.digester.DocumentProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Send2 class
 *
 * @author lishuanwang
 * @date 2019/7/14
 */
public class Send2 {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.obatain factory
        ConnectionFactory factory = new ConnectionFactory();
        //2.set host
        factory.setHost("192.168.111.132");
        //3.create a new connection, a new channel
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
            //4.use the created channel declare a queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //5.create a message
            String message = "hello world";
            //6.finally we can publish our message to the created queue
            channel.basicPublish("", QUEUE_NAME, false, null, message.getBytes("UTF-8"));
        }


    }
}
