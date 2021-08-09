package over35.samples.security.controller

import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import over35.samples.security.SecuritySamplesTest

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author: daibin* @date: 2021/7/28 4:03 下午
 */
class LoginRestControllerTest extends SecuritySamplesTest {

    String path = "/auth"

    @Test
    void testLogin() {
        String username = "admin";
        String password = "admin";

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders
                .post(path + "/login")
                .param("username", username)
                .param("password", password)
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:90.0) Gecko/20100101 Firefox/90.0");
        MvcResult mvcResult = mockMvc
                .perform(servletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
