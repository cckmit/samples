package over35.samples.amqp.api;

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
 * 简单模式
 * @author: daibin
 * @date: 2021/8/13 10:03 下午
 */
@Slf4j
public class SimpleAPI extends AbstractAPI {

    static String getQueueName() {
        return "simple-queue";
    }

    static class Sender extends AbstractClient implements AbstractClient.Sender {

        @SneakyThrows
        public Sender() {
            beforeEach();
        }

        @Override
        public void send(String message) throws IOException {
            {
                // 声明队列,如果队列已存在则不需要
//        channel.queueDeclare(queueName, false, false, false, null);
            }
            channel.basicPublish("", getQueueName(), null, message.getBytes(StandardCharsets.UTF_8));
            log.info(" [x] Sent:{}", message);
        }

        @Override
        protected String getQueueName() {
            return SimpleAPI.getQueueName();
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
            channel.queueDeclare(getQueueName(), true, false, false, null);
        }

        @Override
        protected String getQueueName() {
            return SimpleAPI.getQueueName();
        }

        @Override
        public void recv() throws IOException {
            {
                // 声明队列,如果队列已存在则不需要
//            AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queueName, false, false, false, null);
//            log.info(declareOk.getQueue());
//            log.info(declareOk.getMessageCount());
            }
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info(" [x] Received {}", message);
            };

            channel.basicConsume(getQueueName(), true, deliverCallback, consumerTag -> {
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
