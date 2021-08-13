package over35.samples.shardingjdbc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: daibin
 * @date: 2021/7/31 12:22 上午
 */
@MapperScan("samples.shardingjdbc.mapper")
@Configuration
public class MybatisConfig {
}
