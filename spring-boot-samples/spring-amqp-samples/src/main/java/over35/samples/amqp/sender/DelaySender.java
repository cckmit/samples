package over35.samples.amqp.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import over35.samples.amqp.model.Order;

import static over35.samples.amqp.config.MqConstant.*;

/**
 * @author: daibin
 * @date: 2021/8/16 8:54 下午
 */
@Slf4j
@Component
public class DelaySender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE,DELAY_ROUTING_KEY,order,message -> {
            message.getMessageProperties().setDelay(DELAY_TIME);
            return message;
        });
        log.info("发送信息：{}", order);
    }
}
