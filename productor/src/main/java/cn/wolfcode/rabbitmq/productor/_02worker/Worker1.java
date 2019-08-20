package cn.wolfcode.rabbitmq.productor._02worker;

import com.rabbitmq.client.*;

import java.util.concurrent.TimeUnit;

/**
 * Worker1 class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
public class Worker1 {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.111.132");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true,false,  false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        //每次从消息队列中拿多少个消息.
        channel.basicQos(1);
        //数据传送过来的时候,会激活这个回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("worker1 [x] Received '" + message + "'");
                System.out.println(consumerTag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        //基础消费,关闭自动应答.
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }
}
