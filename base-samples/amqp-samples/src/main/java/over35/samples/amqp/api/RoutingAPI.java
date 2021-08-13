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
 * @author: daibin
 * @date: 2021/8/13 10:02 下午
 */
@Slf4j
public class RoutingAPI extends AbstractAPI {

    static String getExchange() {
        return "Routing-Exchange";
    }

    private static String getRouting() {
        return "R-Routing";
    }

    static class Sender extends AbstractClient implements AbstractClient.Sender {
        @SneakyThrows
        public Sender() {
            beforeEach();
        }

        @Override
        protected String getExchange() {
            return RoutingAPI.getExchange();
        }

        @Override
        protected String getRouting() {
            return RoutingAPI.getRouting();
        }

        @Override
        public void send(String message) throws IOException {

            channel.basicPublish(getExchange(), getRouting(), null, message.getBytes(StandardCharsets.UTF_8));
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
        protected String getExchange() {
            return RoutingAPI.getExchange();
        }

        @Override
        protected String getRouting() {
            return RoutingAPI.getRouting();
        }

        @Override
        protected String getQueueName() throws IOException {
            return "Routing-Queue-" + no;
        }

        @Override
        protected void beforeEach() throws IOException, TimeoutException {
            super.beforeEach();
            // 声明队列
            channel.queueDeclare(getQueueName(), false, false, false, null);
            // 声明交换机
            // 交换类型
            // DIRECT 路由
            // FANOUT 广播
            // TOPIC
            // HEADERS
            channel.exchangeDeclare(getExchange(), BuiltinExchangeType.DIRECT);

            // 绑定交换机 队列
            channel.queueBind(getQueueName(), getExchange(), getRouting());
        }

        @Override
        public void recv() throws IOException {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("Received:{},RoutingKey:{}", message, delivery.getEnvelope().getRoutingKey());
                try {
                    log.info("process ：{}", message);
                } catch (Exception e) {
                    log.error("recv", e);
                } finally {
                    log.info("consumerTag:{},done", consumerTag);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
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
