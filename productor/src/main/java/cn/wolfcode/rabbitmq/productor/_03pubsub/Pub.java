package cn.wolfcode.rabbitmq.productor._03pubsub;

import cn.wolfcode.rabbitmq.productor.utils.ExchangeConstans;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Pub class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
public class Pub {
    private static final String EXCHAGE_NAME = "pub_exchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
            //使用的exchange
            channel.exchangeDeclare(EXCHAGE_NAME, BuiltinExchangeType.FANOUT);

            String message = "info:Hello World";
            channel.basicPublish(EXCHAGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("发布成功:消息[\t"+message+"\t]");
        }

    }
}
