package cn.wolfcode.rabbitmq.productor._01boot_hello;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Recv class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Component
public class Recv {
    //监听boot_hello消息队列
    @RabbitListener(queuesToDeclare = @Queue("boot_hello"))
    public void receiveMsg(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
        System.out.println("当前收到消息:"+msg);
    }
}
