package samples.springboot.shardingjdbc.mapper.mapper

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import samples.springboot.shardingjdbc.ShardingJdbcSamplesTest
import samples.springboot.shardingjdbc.mapper.OrderMapper
import samples.springboot.shardingjdbc.model.Order

/**
 * @author: daibin* @date: 2021/7/31 2:36 上午
 */
class OrderMapperTest extends ShardingJdbcSamplesTest {
    @Autowired
    OrderMapper orderMapper

    @Test
    void test_insert() {

        Order param = new Order(type: 2, state: 1)

        int row = orderMapper.insert(param)

        println row

        println orderMapper.selectAll()

    }

    @Test
    void test_selectByUsername() {
        println orderMapper.selectByPrimaryKey(1)
    }

    @Test
    void test_selectAll() {

//        println userMapper.selectAll()
        println orderMapper.selectAll()

    }
}
