package samples.base.amqp.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import samples.base.amqp.model.Order;

import static samples.base.amqp.config.MqConstant.SIMPLE_QUEUE;

/**
 * @author: daibin
 * @date: 2021/8/16 4:43 下午
 */
@Slf4j
@RabbitListener(queues = SIMPLE_QUEUE)
@Component
public class SimpleConsumer {

    @SneakyThrows
    @RabbitHandler
    public void handle(Order order, Message message, Channel channel) {

        // 未被确认前，不消费新的消息
        channel.basicQos(1);

        try {
            log.info("---接受信息:{}", order);
            String s = null;
            s.equals("null");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            log.error("异常", e);
        } finally {
        }
    }
}
