package samples.springboot.task

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.lang.management.ManagementFactory
import java.lang.management.RuntimeMXBean

/**
 * @author: daibin* @date: 2021/8/4 1:00 下午
 */
@Slf4j
@SpringBootTest
class TaskSamplesTest implements ApplicationContextAware {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor
    @Autowired
    Map<String, TaskExecutor> taskExecutorMap

    ApplicationContext applicationContext

    @BeforeEach
    void testBeforeEach() {
        println '操作 testBeforeEach ......'

    }

    @AfterEach
    void testAfterEach() {

        println '操作 testAfterEach ......'
//
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String mXBeanName = runtimeMXBean.getName()

//        println mXBeanName
        String pid = mXBeanName.split('@')[0]
        println pid

        Process process = Runtime.getRuntime().exec(' kill -15 ' + pid)

        log.warn("end ...... ")
    }

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext
    }
}
