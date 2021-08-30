package samples.springboot.cxf.jaxws.web.impl;

import samples.springboot.cxf.jaxws.web.ProductWsApi;
import samples.springboot.cxf.jaxws.web.dto.Product;

import javax.jws.WebService;
import java.util.Random;

/**
 * @author: daibin
 * @date: 2021/10/16 8:07 上午
 */
@WebService(
        targetNamespace = "http://jaxws.cxf.webservice.base.samples/",// 接口包名倒写
        endpointInterface = "samples.springboot.cxf.jaxws.web.ProductWsApi")
public class ProductWsApiImpl implements ProductWsApi {


    @Override
    public Product getProduct(String productCode) {
        return Product.builder()
                .productCode(productCode)
                .productName("Iphone" + new Random(12) + 1)
                .build();
    }
}
