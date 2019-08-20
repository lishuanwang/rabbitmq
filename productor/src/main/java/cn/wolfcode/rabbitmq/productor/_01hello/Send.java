package cn.wolfcode.rabbitmq.productor._01hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Send class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
public class Send {
    //队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        //配置连接工厂的主机
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        //从连接工厂获取一个链接
        //因为connection和channel实现了closeed接口,所以是可以自动关闭的
        try (Connection connection = factory.newConnection();
             //获取channel
             Channel channel = connection.createChannel()) {
            //设置一个消息队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //消息
            String message = "Hello World!";
            //使用当前链接信道发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
