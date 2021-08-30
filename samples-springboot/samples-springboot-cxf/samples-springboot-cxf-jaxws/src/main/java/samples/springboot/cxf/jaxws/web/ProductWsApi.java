package samples.springboot.cxf.jaxws.web;

import samples.springboot.cxf.jaxws.web.dto.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author: daibin
 * @date: 2021/10/16 8:06 上午
 */
@WebService(
        name = "Product",// 服务名称
        targetNamespace = "http://jaxws.cxf.webservice.base.samples/"// 接口包名倒写
)
public interface ProductWsApi {

    @WebMethod
    Product getProduct(String productCode);
}
