package cn.wolfcode.rabbitmq.productor._01boot_hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Send class
 *
 * @author lishuanwang
 * @date 2019/7/6
 */
@Controller
public class Send {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("hello")
    @ResponseBody
    public String hello(String str){
        //因为是简单的消息发送,使用默认的exchanger,不需要使用
        //第一个是交换机,如果不指定就是默认的,第二个参数是路由key,第三个参数是消息
        //将java对象转换成为消息,然后发送给交换机
        rabbitTemplate.convertAndSend("", "boot_hello", str);
        return "发送完毕";
    }


}
