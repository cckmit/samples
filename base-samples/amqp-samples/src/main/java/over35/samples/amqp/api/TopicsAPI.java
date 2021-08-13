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
public class TopicsAPI extends AbstractAPI {

    private static String getExchange() {
        return "Topics-Exchange";
    }


    static class Sender extends AbstractClient implements AbstractClient.Sender {
        @SneakyThrows
        public Sender() {
            beforeEach();
        }

        @Override
        protected String getExchange() {
            return TopicsAPI.getExchange();
        }

        @Override
        public void send(String message) throws IOException {

//            channel.basicPublish(getExchange(), getRouting(), null, message.getBytes(StandardCharsets.UTF_8));
//            log.info(" [x] Sent:{}", message);

        }

        public void send(String message, String routingKey) throws IOException {
            channel.basicPublish(getExchange(), routingKey, null, message.getBytes(StandardCharsets.UTF_8));
            log.info(" [x] Sent:{}", message);
        }
    }


    static class Receiver extends AbstractClient implements AbstractClient.Receiver {

        @Getter
        int no;

        @Getter
        String routingKey;

        @SneakyThrows
        public Receiver(int no) {
            this.no = no;
            this.beforeEach();
        }

        @SneakyThrows
        public Receiver(int no, String routingKey) {
            this.no = no;
            this.routingKey = routingKey;
            this.beforeEach();
        }

        @Override
        protected String getExchange() {
            return TopicsAPI.getExchange();
        }

        @Override
        public String getRouting() {
            return routingKey;
        }

        @Override
        protected String getQueueName() throws IOException {
            return "Topics-" + no;
        }

        @Override
        protected void beforeEach() throws IOException, TimeoutException {
            super.beforeEach();
            // 声明队列
            channel.queueDeclare(getQueueName(), true, false, false, null);
            // 声明交换机
            // 交换类型
            // DIRECT
            // FANOUT
            // TOPIC
            // HEADERS
//            # 匹配一个或者多个   r.#   r.abc
//            *       匹配一个    r.*   r.a.b.c
            channel.exchangeDeclare(getExchange(), BuiltinExchangeType.TOPIC);

            // 绑定交换机 队列
            channel.queueBind(getQueueName(), getExchange(), getRouting());
        }

        @Override
        public void recv() throws IOException {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info("Received:NO{},message:{},RoutingKey:{}", no, message, delivery.getEnvelope().getRoutingKey());
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
        add(new Receiver(1, "route"));
        add(new Receiver(2, "route.*"));
        add(new Receiver(3, "route。#"));

        Sender sender = new Sender();
        String cmd;
        System.out.println("输入");
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            if ("exit".equals(cmd = bufferedReader.readLine())) {
                break;
            }
            String[] cmds = cmd.split(",");
            sender.send(cmds[0], cmds[1]);
        }

        System.out.println("end");
        System.exit(0);
    }

}
