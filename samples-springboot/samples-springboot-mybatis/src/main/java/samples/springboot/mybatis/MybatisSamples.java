package samples.springboot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: daibin
 * @date: 2021/7/31 12:09 上午
 */
@PropertySource(value = "classpath:config-jdbc.properties")
@SpringBootApplication
public class MybatisSamples {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSamples.class, args);
    }
}