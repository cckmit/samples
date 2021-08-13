package over35.samples.amqp.consumer;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import over35.samples.amqp.model.Order;

import static over35.samples.amqp.config.MqConstant.WORK_QUEUES_QUEUE;

/**
 * @author: daibin
 * @date: 2021/8/16 6:26 下午
 */
@Slf4j
@RabbitListener(queues = WORK_QUEUES_QUEUE)
public class WorkQueuesConsumer {

    private int no;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public WorkQueuesConsumer(int no) {
        this.no = no;
    }

    @SneakyThrows
    @RabbitHandler
    public void handle(Order order, Message message, Channel channel) {

        try {
            log.info("---{}.接受信息:{}", no, order);
        }catch (Exception e){
            log.error("异常", e);
        }finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
        }
    }

}
