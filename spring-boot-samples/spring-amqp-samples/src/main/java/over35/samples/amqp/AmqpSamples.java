package over35.samples.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author: daibin
 * @date: 2021/8/13 9:40 下午
 */
@EnableConfigurationProperties
@SpringBootApplication
public class AmqpSamples {


    public static void main(String[] args) {
        SpringApplication.run(AmqpSamples.class, args);
    }
}
