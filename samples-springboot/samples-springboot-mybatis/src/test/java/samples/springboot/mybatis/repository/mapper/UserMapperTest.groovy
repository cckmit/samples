package samples.springboot.mybatis.repository.mapper

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import samples.springboot.mybatis.repository.model.User
import samples.springboot.mybatis.MybatisSamplesTest

import static org.mybatis.dynamic.sql.SqlBuilder.equalTo
import static UserDynamicSqlSupport.username

/**
 * @author: daibin* @date: 2021/7/31 12:34 上午
 */
class UserMapperTest extends MybatisSamplesTest {

    @Autowired
    UserMapper userMapper

    @Autowired
    JdbcTemplate jdbcTemplate

    @Test
    void test_selectByUsername() {
        def user = userMapper.selectByUsername('username_1')
        println user
    }

    @Test
    void test_selectAll() {
        def user = userMapper.select(c->c)
        println user
    }

    @Test
    void test_select() {
        println userMapper.select(c -> c.where(username, isEqualTo('username_1')))
    }

    @Test
    void test_update() {
        User user = new User(username: 't_username_2',password: 't_p_1',enabled: false)
        println userMapper.update(user)
    }

    @Test
    void test_insert() {
        User user = new User(username: 't_username_3',password: 't_p_1',enabled: false)
        println userMapper.insert(user)
    }

}
