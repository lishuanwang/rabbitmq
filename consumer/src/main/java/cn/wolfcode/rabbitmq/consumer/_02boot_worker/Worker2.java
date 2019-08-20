package cn.wolfcode.rabbitmq.consumer._02boot_worker;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Worker class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("boot_worker"))
public class Worker2 {

    public void receiveMsg(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("工作者2:"+msg);
        channel.basicAck(deliveryTag,false);
    }

}
