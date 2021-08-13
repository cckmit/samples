package over35.samples.amqp.sender

import cn.hutool.core.lang.generator.SnowflakeGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import over35.samples.amqp.AmqpSamplesTest
import over35.samples.amqp.config.MqConstant
import over35.samples.amqp.model.Order

import java.util.concurrent.TimeUnit

/**
 * @author: daibin* @date: 2021/8/16 9:04 下午
 */
class DelaySenderTest extends AmqpSamplesTest{

    @Autowired
    DelaySender sender

    SnowflakeGenerator snowflakeGenerator  = new SnowflakeGenerator()

    @Test
    void test_send(){
        Order order = new Order(id: snowflakeGenerator.next(),state: 'ON')
        sender.send(order)

        TimeUnit.SECONDS.sleep(MqConstant.DELAY_TIME+1000L)
    }
}
