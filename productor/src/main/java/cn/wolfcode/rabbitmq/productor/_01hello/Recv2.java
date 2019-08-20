package cn.wolfcode.rabbitmq.productor._01hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.sun.xml.internal.ws.spi.db.DatabindingException;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Recv2 class
 *
 * @author lishuanwang
 * @date 2019/7/14
 */
public class Recv2 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(message.getBody());
            System.out.println(new String(message.getBody(), "UTF-8"));
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
