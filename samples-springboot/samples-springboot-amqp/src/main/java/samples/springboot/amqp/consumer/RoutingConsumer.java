package samples.base.amqp.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import samples.base.amqp.model.Order;

import static samples.base.amqp.config.MqConstant.*;

/**
 * @author: daibin
 * @date: 2021/8/16 7:35 下午
 */
@Slf4j
@Component
public class RoutingConsumer {


    @SneakyThrows
    @RabbitListener(queues = ROUTING_QUEUE_PREFIX +1)
    @RabbitHandler
    public void handle1(Order order, Message message, Channel channel) {

        try {
            log.info("---1.接受信息:{}", order);
        } catch (Exception e) {
            log.error("异常", e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @SneakyThrows
    @RabbitListener(queues = ROUTING_QUEUE_PREFIX +2)
    @RabbitHandler
    public void handle2(Order order, Message message, Channel channel) {

        try {
            log.info("---2.接受信息:{}", order);
        } catch (Exception e) {
            log.error("异常", e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @SneakyThrows
    @RabbitListener(queues = ROUTING_QUEUE_PREFIX +3)
    @RabbitHandler
    public void handle3(Order order, Message message, Channel channel) {

        try {
            log.info("---3.接受信息:{}", order);
        } catch (Exception e) {
            log.error("异常", e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
