package samples.springboot.mybatis.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import samples.springboot.mybatis.MybatisSamplesTest

/**
 * @author: daibin* @date: 2021/8/8 9:38 下午
 */
class UserServiceTest extends MybatisSamplesTest {
    @Autowired
    UserService userService

    @Test
    void test_getUserByRole() {
        println userService.getUserByRole('role_1')
    }
}
