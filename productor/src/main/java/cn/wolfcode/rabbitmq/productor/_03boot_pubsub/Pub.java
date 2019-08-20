package cn.wolfcode.rabbitmq.productor._03boot_pubsub;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Pub class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Controller
public class Pub {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("sub")
    @ResponseBody
    public String sub(String msg){
        rabbitTemplate.convertAndSend("boot_pub", "", msg);
        return "消息发布完成"+msg;
    }
}
