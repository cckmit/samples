package samples.springboot.cxf.jaxws.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: daibin
 * @date: 2021/10/16 8:19 下午
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private String username;

    private String name;
}
