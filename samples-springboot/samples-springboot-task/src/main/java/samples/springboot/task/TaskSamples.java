package samples.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: daibin
 * @date: 2021/8/4 9:53 上午
 */
@Slf4j
@SpringBootApplication
public class TaskSamples implements DisposableBean {

    public static void main(String[] args) {
        SpringApplication.run(TaskSamples.class, args);
    }

    @Override
    public void destroy() throws Exception {
        log.warn("正在停机做Shutdown 操作 destroy......");
    }
}
