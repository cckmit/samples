package over35.samples.shardingjdbc.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/7/31 12:23 上午
 */
@Entity
@Table(name = "t_order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -1688121726408235152L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "state", nullable = false)
    private int state;
}
