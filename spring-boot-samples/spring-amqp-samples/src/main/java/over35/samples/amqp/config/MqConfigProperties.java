package over35.samples.amqp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author: daibin
 * @date: 2021/8/16 5:12 下午
 */
@ConfigurationProperties(prefix = "mq")
public class MqConfigProperties {

    Map<String,MqProperties> config;
}
