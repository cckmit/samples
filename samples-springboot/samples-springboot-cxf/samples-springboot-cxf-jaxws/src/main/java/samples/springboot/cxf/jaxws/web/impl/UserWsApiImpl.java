package samples.springboot.cxf.jaxws.web.impl;

import samples.springboot.cxf.jaxws.web.UserWsApi;
import samples.springboot.cxf.jaxws.web.dto.User;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.UUID;

/**
 * @author: daibin
 * @date: 2021/10/16 8:07 上午
 */
@WebService(
        targetNamespace = "http://web.jaxws.cxf.springboot.samples/",// 接口包名倒写
        endpointInterface = "samples.springboot.cxf.jaxws.web.UserWsApi")
public class UserWsApiImpl implements UserWsApi {

    @Override
    public User getUser(@WebParam(name = "username") String username) {
        return User.builder()
                .username(username)
                .name("test" + UUID.randomUUID())
                .build();
    }
}
