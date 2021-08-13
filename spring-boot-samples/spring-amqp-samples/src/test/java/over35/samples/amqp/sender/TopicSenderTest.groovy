package over35.samples.amqp.sender;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import over35.samples.amqp.AmqpSamplesTest;
import over35.samples.amqp.model.Order;

/**
 * @author: daibin* @date: 2021/8/16 8:08 下午
 */
class TopicSenderTest extends AmqpSamplesTest {

    @Autowired
    TopicSender sender

    SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator()

    @Test
    void test_send() {

        Order order1 = new Order(id: snowflakeGenerator.next(), state: 'ON')
        sender.send(order1, "route")

        Order order2 = new Order(id: snowflakeGenerator.next(), state: 'ON')
        sender.send(order2, "route.a")

        Order order3 = new Order(id: snowflakeGenerator.next(), state: 'ON')
        sender.send(order3, "route.a.b")


//        TimeUnit.SECONDS.sleep(2L)
    }
}
