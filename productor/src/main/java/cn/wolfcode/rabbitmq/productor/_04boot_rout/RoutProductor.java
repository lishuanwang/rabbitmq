package cn.wolfcode.rabbitmq.productor._04boot_rout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RoutProductor class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Controller
public class RoutProductor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("rout")
    @ResponseBody
    public String rout(String msg){
        rabbitTemplate.convertAndSend("bootrout", "info", msg);
        return "发送数据:"+msg;
    }

}
