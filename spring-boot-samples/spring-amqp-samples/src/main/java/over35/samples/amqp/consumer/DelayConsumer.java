package over35.samples.amqp.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import over35.samples.amqp.model.Order;

import static over35.samples.amqp.config.MqConstant.DELAY_QUEUE;

/**
 * @author: daibin
 * @date: 2021/8/16 8:54 下午
 */
@Slf4j
@RabbitListener(queues = DELAY_QUEUE)
@Component
public class DelayConsumer {

    @SneakyThrows
    @RabbitHandler
    public void handle(Order order, Message message, Channel channel) {

        // 未被确认前，不消费新的消息
        channel.basicQos(1);

        try {
            log.info("---接受信息:{}", order);
        } catch (Exception e) {
            log.error("异常", e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
