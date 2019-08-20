package cn.wolfcode.rabbitmq.consumer._03boot_pubsub;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


/**
 * Sub class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Component
public class Sub2 {

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "boot_pub", type = "fanout")))
    public void recvPub(String msg, @Header(AmqpHeaders.DELIVERY_TAG)Long deliveryTag, Channel channel) throws Exception{
        System.out.println("SUB2收到消息"+msg);
        channel.basicAck(deliveryTag, false);
    }

}
