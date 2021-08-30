package samples.base.amqp.sender

import cn.hutool.core.lang.generator.SnowflakeGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import samples.base.amqp.AmqpSamplesTest
import samples.base.amqp.model.Order

import java.util.concurrent.TimeUnit

/**
 * @author: daibin* @date: 2021/8/16 6:11 下午
 */
class SimpleSenderTest extends AmqpSamplesTest{

    @Autowired
    SimpleSender sender

    SnowflakeGenerator snowflakeGenerator  = new SnowflakeGenerator()

    @Test
    void test_send(){
        Order order = new Order(id: snowflakeGenerator.next(),state: 'ON')
        sender.send(order)

        TimeUnit.SECONDS.sleep(5L)
    }
}
