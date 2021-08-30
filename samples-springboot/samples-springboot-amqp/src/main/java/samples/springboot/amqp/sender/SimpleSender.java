package samples.base.amqp.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import samples.base.amqp.model.Order;
import samples.base.amqp.config.MqConstant;

/**
 * @author: daibin
 * @date: 2021/8/16 4:52 下午
 */
@Slf4j
@Component
public class SimpleSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
//        消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器,消息发送到交换机确认机制
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("correlationData.id:{},correlationData.returnedMessage:{},ack:{},cause:{}", correlationData != null ? correlationData.getId() : null, correlationData != null ? correlationData.getReturned() : null, ack, cause));
//        启动消息失败返回,当消息从交换机到队列失败时,该方法被调用
        rabbitTemplate.setReturnsCallback(returned -> log.info("ReturnedMessage:{}", returned));
        rabbitTemplate.convertAndSend(MqConstant.SIMPLE_QUEUE, order);
        log.info("发送信息：{}", order);
    }
}
