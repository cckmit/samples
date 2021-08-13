package over35.samples.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: daibin
 * @date: 2021/8/13 10:04 下午
 */
public abstract class AbstractClient {

    ConnectionFactory connectionFactory;

    Connection connection;

    protected Channel channel;

    protected String getQueueName() throws IOException {
        return null;
    }

    protected String getExchange() {
        return null;
    }

    protected String getRouting() {
        return null;
    }

    protected void beforeEach() throws IOException, TimeoutException {
        AmqpProperties properties = new AmqpProperties();
        properties.setHost("localhost");
        properties.setPort(5662);
        properties.setUsername("guest");
        properties.setPassword("guest");

        connectionFactory = connectionFactory(properties);
        connection = connection(connectionFactory);
        channel = channel(connection);
    }


    protected void afterEach() throws Exception {
        if (channel != null) {
            channel.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    ConnectionFactory connectionFactory(AmqpProperties properties) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(properties.getHost());
        factory.setPort(properties.getPort());
        factory.setUsername(properties.getUsername());
        factory.setPassword(properties.getPassword());
        return factory;
    }

    Connection connection(ConnectionFactory factory) throws IOException, TimeoutException {
        return factory.newConnection();
    }

    Channel channel(Connection connection) throws IOException {
        return connection.createChannel();
    }

    public interface Sender {

        void send(String message) throws IOException;
    }

    public interface Receiver {

        int getNo();

        void recv() throws IOException;
    }

    public static void main(String[] args) {

    }
}
