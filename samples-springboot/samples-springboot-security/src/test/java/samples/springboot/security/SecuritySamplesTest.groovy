package samples.springboot.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

/**
 * @author: daibin* @date: 2021/7/28 3:13 下午
 */
@AutoConfigureMockMvc
@SpringBootTest
class SecuritySamplesTest {

    @Autowired
    MockMvc mockMvc

}
