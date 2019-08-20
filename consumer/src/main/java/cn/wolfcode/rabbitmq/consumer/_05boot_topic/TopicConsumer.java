package cn.wolfcode.rabbitmq.consumer._05boot_topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * TopicConsumer class
 *
 * @author lishuanwang
 * @date 2019/7/7
 */
@Component
public class TopicConsumer {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "boot_topic",type = "topic"),
            key = "order.*"
    ))
    public void receiveMsg(String msg, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag, Channel channel) throws Exception {
        System.out.println("topic1收取消息:"+msg);
        channel.basicAck(deliveryTag,false);
    }
}
