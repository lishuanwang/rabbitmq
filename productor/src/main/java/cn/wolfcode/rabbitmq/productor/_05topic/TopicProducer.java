package cn.wolfcode.rabbitmq.productor._05topic;

import cn.wolfcode.rabbitmq.productor.utils.ExchangeConstans;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * TopicProducer class
 *
 * @author lishuanwang
 * @date 2019/7/7
 */
public class TopicProducer {
    public static final String EXCHANGE_NAME = "topic";
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ExchangeConstans.MQ_SERVER_IP);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = "order.save";
            String message = "topicMsg";

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }
}
