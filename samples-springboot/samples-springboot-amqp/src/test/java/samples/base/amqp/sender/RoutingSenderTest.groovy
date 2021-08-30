package samples.base.amqp.sender

import cn.hutool.core.lang.generator.SnowflakeGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import samples.base.amqp.AmqpSamplesTest
import samples.base.amqp.model.Order

/**
 * @author: daibin* @date: 2021/8/16 7:41 下午
 */
class RoutingSenderTest extends AmqpSamplesTest{

    @Autowired
    RoutingSender sender

    SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator()

    @Test
    void test_send() {
        Order order = new Order(id: snowflakeGenerator.next(), state: 'ON')
        sender.send(order)

//        TimeUnit.SECONDS.sleep(2L)
    }
}
