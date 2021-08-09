package over35.samples.redisson.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/8/3 12:13 下午
 */
@Table(name = "t_stock")
@Data
public class Stock implements Serializable {

    private static final long serialVersionUID = 3088760509091552807L;

    @Id
    private Long id;

    private Long productId;

    private Long quantity;
}
