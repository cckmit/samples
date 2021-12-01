package samples.springboot.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: daibin
 * @date: 2021/7/31 12:22 上午
 */
@MapperScan("samples.springboot.mybatis.repository.mapper")
@Configuration
public class MybatisConfig {
}
