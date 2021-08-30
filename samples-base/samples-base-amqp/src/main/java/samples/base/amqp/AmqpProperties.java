package samples.base.amqp;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: daibin
 * @date: 2021/8/13 10:15 下午
 */
@Accessors(chain = true)
@Setter
@Getter
public class AmqpProperties {

    private String host;

    private int port;

    private String username;

    private String password;
}
