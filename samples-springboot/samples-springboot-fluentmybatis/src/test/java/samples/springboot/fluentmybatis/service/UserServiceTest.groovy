package samples.springboot.fluentmybatis.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import samples.springboot.fluentmybatis.FluentMybatisSamplesTest

/**
 * @author: daibin* @date: 2021/7/30 5:32 下午
 */
class UserServiceTest extends FluentMybatisSamplesTest{

    @Autowired
    UserService userService

    @Test
    void test_getUserByUsername(){
        def user = userService.getUserByUsername('username_1')
        println user
    }

    @Test
    void test_getUserByRole() {
        def users = userService.getUserByRole('role_1')
        println users
    }

}
