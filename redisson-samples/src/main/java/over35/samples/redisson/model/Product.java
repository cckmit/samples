package over35.samples.redisson.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/8/3 12:20 下午
 */
@Table(name = "t_product")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = -7237516068733493517L;

    @Id
    private Long id;

    private String name;
}
