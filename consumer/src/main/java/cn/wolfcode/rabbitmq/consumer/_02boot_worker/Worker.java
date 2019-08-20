package cn.wolfcode.rabbitmq.consumer._02boot_worker;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Worker class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Component
public class Worker {
    @RabbitListener(queuesToDeclare = @Queue("boot_worker"))
    /**
     * deliverytag:消息的编号
     * channel:发送应答
     */
    public void receiveMsg(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("工作者1:"+msg);
        //in our application.properties, we config ack the mq by hand.
        channel.basicAck(deliveryTag,false);
    }

}
