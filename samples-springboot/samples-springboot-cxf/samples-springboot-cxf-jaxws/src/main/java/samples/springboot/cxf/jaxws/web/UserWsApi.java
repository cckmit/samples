package samples.springboot.cxf.jaxws.web;

import samples.springboot.cxf.jaxws.web.dto.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: daibin
 * @date: 2021/10/16 8:06 上午
 */
@WebService(
        name = "User",// 服务名称
        targetNamespace = "http://web.jaxws.cxf.springboot.samples/"// 接口包名倒写
)
public interface UserWsApi {

    @WebMethod
    User getUser(@WebParam(name = "username") String username);
}
