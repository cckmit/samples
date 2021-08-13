package over35.samples.amqp.sender

import cn.hutool.core.lang.generator.SnowflakeGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import over35.samples.amqp.AmqpSamplesTest
import over35.samples.amqp.model.Order

/**
 * @author: daibin* @date: 2021/8/16 6:28 下午
 */
class WorkQueuesSenderTest extends AmqpSamplesTest{

    @Autowired
    WorkQueuesSender sender

    SnowflakeGenerator snowflakeGenerator  = new SnowflakeGenerator()

    @Test
    void test_send(){

        for (i in 0..<10) {
            Order order = new Order(id: snowflakeGenerator.next(),state: 'ON')
            sender.send(order)
        }


//        TimeUnit.SECONDS.sleep(2L)
    }
}
