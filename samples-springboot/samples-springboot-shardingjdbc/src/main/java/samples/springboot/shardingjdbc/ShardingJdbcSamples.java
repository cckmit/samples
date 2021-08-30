package samples.springboot.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * @author: daibin
 * @date: 2021/7/31 1:57 上午
 */
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingJdbcSamples {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcSamples.class, args);
    }

}
