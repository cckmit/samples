package samples.springboot.redisson.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/8/3 12:31 下午
 */
@Table(name = "t_order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -4160482026444968208L;
    @Id
    private Long id;

    private Long productId;

    private Long purchaseQuantity;
}
