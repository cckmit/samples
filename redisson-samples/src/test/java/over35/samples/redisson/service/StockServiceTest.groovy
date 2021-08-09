package over35.samples.redisson.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import over35.samples.redisson.RedissonSamplesTest

/**
 * @author: daibin* @date: 2021/8/3 1:28 下午
 */
class StockServiceTest extends RedissonSamplesTest{

    @Autowired
    StockService stockService

    @Test
    void test(){

        stockService.decreaseStock(1001L,100L)
    }
}
