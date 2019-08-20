package cn.wolfcode.rabbitmq.productor._02boot_worker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * WorkerController class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Controller
public class WorkerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("worker")
    @ResponseBody
    public String workerPattern(String msg){
        for (int i=0; i<20; i++){
            rabbitTemplate.convertAndSend("", "boot_worker", msg+i);
        }

        return "消息发送完毕";
    }

}
