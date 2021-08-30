package samples.springboot.task.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author: daibin
 * @date: 2021/8/3 6:42 下午
 */
@Setter
@Getter
@ConfigurationProperties("spring.task")
public class TaskProperties {

    private Map<String, TaskExecutionProperties> execution;

    private Map<String, TaskSchedulingProperties> scheduling;
}
