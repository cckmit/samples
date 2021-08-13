package over35.samples.amqp.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import over35.samples.amqp.model.Order;

import static over35.samples.amqp.config.MqConstant.PUBLISH_SUBSCRIBE_EXCHANGE;

/**
 * @author: daibin
 * @date: 2021/8/16 7:11 下午
 */
@Slf4j
@Component
public class PublishSubscribeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
        rabbitTemplate.convertAndSend(PUBLISH_SUBSCRIBE_EXCHANGE,"",order);
        log.info("发送信息：{}", order);
    }
}
