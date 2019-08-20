package cn.wolfcode.rabbitmq.productor._05boot_topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TopicProducer class
 *
 * @author lishuanwang
 * @date 2019/7/7
 */
@Controller
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("topic")
    @ResponseBody
    public String topic(String msg){
        rabbitTemplate.convertAndSend("boot_topic", "order.fkajsdfkl", msg);
        return "发送数据:"+msg;
    }

}
