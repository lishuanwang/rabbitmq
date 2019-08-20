package cn.wolfcode.rabbitmq.consumer._04boot_rout;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * RoutConsumer class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Component
public class RoutConsumer2 {

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "rout",
                    type = "direct"), key = {"error","info"}))
    public void rout(String msg, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag, Channel channel) throws Exception{

        System.out.println("error&info 收到消息:"+msg);
        channel.basicAck(deliveryTag,false);

    }

}
