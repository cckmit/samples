package over35.samples.amqp;


import lombok.Getter;
import lombok.Setter;

/**
 * @author: daibin
 * @date: 2021/8/13 10:15 下午
 */
@Setter
@Getter
public class AmqpProperties {

    private String host;

    private int port;

    private String username;

    private String password;
}
