package samples.base.amqp.sender

import cn.hutool.core.lang.generator.SnowflakeGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import samples.base.amqp.AmqpSamplesTest
import samples.base.amqp.config.MqConstant
import samples.base.amqp.model.Order

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
