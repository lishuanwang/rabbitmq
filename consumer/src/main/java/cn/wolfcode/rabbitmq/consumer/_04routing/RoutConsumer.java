package cn.wolfcode.rabbitmq.consumer._04routing;

import com.rabbitmq.client.*;

import java.util.Queue;

/**
 * RoutConsumer class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
public class RoutConsumer {

    private static final String EXCHANGE_NAME = "routing";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_NAME, "info:");
        channel.queueBind(queue, EXCHANGE_NAME, "error:");
        channel.queueBind(queue, EXCHANGE_NAME, "warning:");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //收到数据的回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
        });


    }

}

