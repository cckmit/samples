package over35.samples.jpa.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import over35.samples.jpa.JpaSamplesTest
import over35.samples.jpa.model.User

/**
 * @author: daibin* @date: 2021/7/31 1:12 上午
 */
class UserRepositoryTest extends JpaSamplesTest {

    @Autowired
    UserRepository userRepository

    @Test
    void test() {
        def optional = userRepository.findById('username_1')
        println optional
    }

    @Test
    void test_queryAllByRole() {
        List<User> list = userRepository.selectAllByRole('role_1')
        println list

    }
}
