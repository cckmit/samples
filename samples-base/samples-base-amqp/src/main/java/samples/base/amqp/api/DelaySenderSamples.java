package samples.base.amqp.api;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DeliverCallback;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import samples.base.amqp.AbstractAPI;
import samples.base.amqp.AbstractClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author: daibin
 * @date: 2021/8/17 10:37 上午
 */
@Slf4j
public class DelaySenderSamples extends AbstractAPI {

    static String getExchange() {
        return "delay_exchange";
    }

    static String getQueueName() {
        return "delay_queue";
    }

    static String getRouting() {
        return "delay_routingKey";
    }

    static class Sender extends AbstractClient implements AbstractClient.Sender {

        @SneakyThrows
        public Sender() {
            beforeEach();
        }

        @Override
        public void send(String message) throws IOException {
            Map<String,Object> headers = new HashMap<>();
            headers.put("x-delay",10*1000);
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties()
                    .builder()
                    .headers(headers)
                    .build();
            channel.basicPublish(getExchange(), getRouting(), basicProperties, message.getBytes(StandardCharsets.UTF_8));
            log.info(" [x] Sent:{}", message);

        }

        @Override
        protected String getExchange() {
            return DelaySenderSamples.getExchange();
        }

        @Override
        protected String getRouting() {
            return DelaySenderSamples.getRouting();
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
            getProperties().setPort(5652);
            super.beforeEach();
            channel.queueDeclare(getQueueName(), true, false, false, null);

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-delayed-type","direct");
            channel.exchangeDeclare(getExchange(), "x-delayed-message",true,false,arguments);

            // 绑定交换机 队列
            channel.queueBind(getQueueName(), getExchange(), getRouting());
        }

        @Override
        protected String getExchange() {
            return DelaySenderSamples.getExchange();
        }

        @Override
        protected String getRouting() {
            return DelaySenderSamples.getRouting();
        }

        @Override
        protected String getQueueName() throws IOException {
            return DelaySenderSamples.getQueueName();
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
