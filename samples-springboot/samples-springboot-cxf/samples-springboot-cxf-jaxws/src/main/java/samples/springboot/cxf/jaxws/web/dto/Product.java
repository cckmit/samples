package samples.springboot.cxf.jaxws.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: daibin
 * @date: 2021/10/18 8:08 上午
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    private String productCode;

    private String productName;
}
