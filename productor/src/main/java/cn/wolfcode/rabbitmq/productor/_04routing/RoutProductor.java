package cn.wolfcode.rabbitmq.productor._04routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.print.DocFlavor;

/**
 * RoutProductor class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
public class RoutProductor {
    public static final String EXCHANGE_NAME = "routing";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        try(Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()){
            //声明一个路由
            channel.exchangeDeclare(EXCHANGE_NAME,  BuiltinExchangeType.DIRECT);
            String severity = "info:";
            String message = "directMsg";

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }
}
