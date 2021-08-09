package over35.samples.task.service

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import over35.samples.task.TaskSamplesTest

/**
 * @author: daibin* @date: 2021/8/4 3:03 下午
 */
@Slf4j
class OrderServiceTest extends TaskSamplesTest {

    @Autowired
    OrderService orderService

    @Test
    void test() {
        orderService.notificationSuccessful()
    }
}
