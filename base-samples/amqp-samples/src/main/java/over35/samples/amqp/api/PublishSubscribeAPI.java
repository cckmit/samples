package over35.samples.amqp.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.DeliverCallback;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import over35.samples.amqp.AbstractAPI;
import over35.samples.amqp.AbstractClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 发布订阅模式
 *
 * @author: daibin
 * @date: 2021/8/13 10:01 下午
 */
@Slf4j
public class PublishSubscribeAPI extends AbstractAPI {

    static String getExchange() {
        return "Pub-Sub-Exchange";
    }

    static class Sender extends AbstractClient implements AbstractClient.Sender {

        @SneakyThrows
        public Sender() {
            beforeEach();
        }

        @Override
        protected String getExchange() {
            return PublishSubscribeAPI.getExchange();
        }

        @Override
        public void send(String message) throws IOException {
            channel.basicPublish(getExchange(), "", null, message.getBytes(StandardCharsets.UTF_8));
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
            // 声明队列
            channel.queueDeclare(getQueueName(), true, false, false, null);
            // 声明交换机
            // 交换类型
            // DIRECT 直连
            // FANOUT 扇形,广播
            // TOPIC  主题，通配符，模糊
            // HEADERS
            channel.exchangeDeclare(getExchange(), BuiltinExchangeType.FANOUT);

            // 绑定交换机 队列
            channel.queueBind(getQueueName(), getExchange(), "");
        }

        @Override
        protected String getQueueName() throws IOException {
            return "Pub-Sub-Queue-" + no;
        }

        @Override
        protected String getExchange() {
            return PublishSubscribeAPI.getExchange();
        }

        @Override
        public void recv() throws IOException {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("Received:{}", message);
                try {
                    log.info("process ：{}", message);
                } catch (Exception e) {
                    log.error("recv", e);
                } finally {
                    log.info("consumerTag:{},done", consumerTag);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                }
            };
            channel.basicConsume(getQueueName(), false, deliverCallback, consumerTag -> {
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
