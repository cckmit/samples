package samples.springboot.redisson.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: daibin
 * @date: 2021/8/3 2:10 下午
 */
@Configuration
@MapperScan("samples.redisson.repository.mapper")
public class MyBatisConfig {
}
