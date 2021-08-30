package samples.base.amqp.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/8/16 5:03 下午
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 6258083173126137495L;

    private long id;

    private String state;
}
