package over35.samples.amqp.api;

import lombok.Getter;
import lombok.SneakyThrows;
import over35.samples.amqp.AbstractAPI;
import over35.samples.amqp.AbstractClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: daibin
 * @date: 2021/8/13 10:03 下午
 */
public class PublisherConfirmsAPI extends AbstractAPI {

    static class Sender extends AbstractClient implements AbstractClient.Sender {
        @Override
        public void send(String message) throws IOException {

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
        public void recv() throws IOException {

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
