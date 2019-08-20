package cn.wolfcode.rabbitmq.productor._02worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Send class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
public class Send {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            String message = "tasequeue";
            for (int i = 0; i < 20; i++) {
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        (message + i).getBytes("UTF-8"));
            }

            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
