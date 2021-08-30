package samples.base.amqp.api;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import samples.base.amqp.AbstractAPI;
import samples.base.amqp.AbstractClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 工作模式
 *
 * @author: daibin
 * @date: 2021/8/13 10:01 下午
 * @see {@link <url>https://www.rabbitmq.com/tutorials/tutorial-two-java.html</url>}
 */
@Slf4j
public class WorkQueuesAPI extends AbstractAPI {

    static String getQueueName() {
        return "work-queues";
    }

    static class Sender extends AbstractClient implements AbstractClient.Sender {

        @SneakyThrows
        public Sender() {
            beforeEach();
        }

        @Override
        protected String getQueueName() {
            return WorkQueuesAPI.getQueueName();
        }

        @Override
        public void send(String message) throws IOException {
            channel.basicPublish("", getQueueName(), MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            log.info(" [x] Sent:{}", message);
        }
    }

    static class Receiver extends AbstractClient implements AbstractClient.Receiver {

        @Getter
        int no;

        @SneakyThrows
        public Receiver(int no) {
            this.no = no;
            this.beforeEach();
        }

        @Override
        protected void beforeEach() throws IOException, TimeoutException {
            super.beforeEach();
            // 持久化 boolean durable = true;
            channel.queueDeclare(getQueueName(), true, false, false, null);
        }

        @Override
        protected String getQueueName() {
            return WorkQueuesAPI.getQueueName();
        }

        @Override
        public void recv() throws IOException {
//            0
//            9
//            1 等待确认后再接收消息
            channel.basicQos(1);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("Received:{}", message);
                try {
                    log.info("process ：{}", message);
                } catch (Exception e) {
                    log.error("recv", e);
                } finally {
                    log.info("consumerTag:{},done", consumerTag);
                    // 确认消息
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };

            boolean autoAck = false;

            channel.basicConsume(getQueueName(), autoAck, deliverCallback, consumerTag -> {
                log.info("consumerTag:{}", consumerTag);
            });
        }
    }

    public static void main(String[] args) throws IOException {

        // sent
        add(new Receiver(1));
        add(new Receiver(2));
        add(new Receiver(3));

        Sender sender = new Sender();
        String cmd;
        System.out.println("输入");
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            if ("exit".equals(cmd = bufferedReader.readLine())) {
                break;
            }
            sender.send(cmd);
        }

        System.out.println("end");
        System.exit(0);
    }

}
