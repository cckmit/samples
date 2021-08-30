package samples.springboot.task.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.boot.task.TaskSchedulerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Map;

/**
 * @author: daibin
 * @date: 2021/8/4 9:57 上午
 */
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties(TaskProperties.class)
@EnableAutoConfiguration(exclude = {TaskExecutionAutoConfiguration.class, TaskSchedulingAutoConfiguration.class})
@Configuration(proxyBeanMethods = false)
public class TaskConfig implements BeanFactoryAware, InitializingBean, DisposableBean {


    @Autowired
    private TaskProperties taskProperties;
    @Autowired
    private ObjectProvider<TaskExecutorCustomizer> taskExecutorCustomizers;
    @Autowired
    private ObjectProvider<TaskDecorator> taskDecorator;
    @Autowired
    private ObjectProvider<TaskSchedulerCustomizer> taskSchedulerCustomizers;

    private DefaultListableBeanFactory beanFactory;


    @Override
    public void afterPropertiesSet() throws Exception {
        // 线程池
        Map<String, TaskExecutionProperties> execution = taskProperties.getExecution();
        if (execution != null) {
            System.out.println(execution);
            execution.forEach((k, v) -> {
                TaskExecutorBuilder builder = TaskExecutorHelper.taskExecutorBuilder(v, taskExecutorCustomizers, taskDecorator);
                ThreadPoolTaskExecutor threadPoolTaskExecutor = TaskExecutorHelper.applicationTaskExecutor(builder);
                // 手动注册,手动执行初始化方法
                threadPoolTaskExecutor.setThreadFactory(threadPoolTaskExecutor);
                threadPoolTaskExecutor.afterPropertiesSet();
                registryBean(k, threadPoolTaskExecutor);
            });
        }
        // 调度
        Map<String, TaskSchedulingProperties> scheduling = taskProperties.getScheduling();
        if (scheduling != null) {
            System.out.println(scheduling);

            scheduling.forEach((k, v) -> {
                TaskSchedulerBuilder builder = TaskSchedulerHelper.taskSchedulerBuilder(v, taskSchedulerCustomizers);
                ThreadPoolTaskScheduler threadPoolTaskScheduler = TaskSchedulerHelper.taskScheduler(builder);
                // 手动注册,手动执行初始化方法
                threadPoolTaskScheduler.afterPropertiesSet();
                registryBean(k, threadPoolTaskScheduler);
            });
        }
    }


    void registryBean(String beanName, Object bean) {
        beanFactory.registerSingleton(beanName, bean);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy....");
    }


    static class TaskExecutorHelper {

        public static TaskExecutorBuilder taskExecutorBuilder(TaskExecutionProperties properties,
                                                              ObjectProvider<TaskExecutorCustomizer> taskExecutorCustomizers,
                                                              ObjectProvider<TaskDecorator> taskDecorator) {
            TaskExecutionProperties.Pool pool = properties.getPool();
            TaskExecutorBuilder builder = new TaskExecutorBuilder();
            builder = builder.queueCapacity(pool.getQueueCapacity());
            builder = builder.corePoolSize(pool.getCoreSize());
            builder = builder.maxPoolSize(pool.getMaxSize());
            builder = builder.allowCoreThreadTimeOut(pool.isAllowCoreThreadTimeout());
            builder = builder.keepAlive(pool.getKeepAlive());
            TaskExecutionProperties.Shutdown shutdown = properties.getShutdown();
            builder = builder.awaitTermination(shutdown.isAwaitTermination());
            builder = builder.awaitTerminationPeriod(shutdown.getAwaitTerminationPeriod());
            builder = builder.threadNamePrefix(properties.getThreadNamePrefix());
            builder = builder.customizers(taskExecutorCustomizers.orderedStream()::iterator);
            builder = builder.taskDecorator(taskDecorator.getIfUnique());
            return builder;
        }

        public static ThreadPoolTaskExecutor applicationTaskExecutor(TaskExecutorBuilder builder) {
            return builder.build();
        }

    }

    static class TaskSchedulerHelper {

        public static TaskSchedulerBuilder taskSchedulerBuilder(TaskSchedulingProperties properties,
                                                                ObjectProvider<TaskSchedulerCustomizer> taskSchedulerCustomizers) {
            TaskSchedulerBuilder builder = new TaskSchedulerBuilder();
            builder = builder.poolSize(properties.getPool().getSize());
            TaskSchedulingProperties.Shutdown shutdown = properties.getShutdown();
            builder = builder.awaitTermination(shutdown.isAwaitTermination());
            builder = builder.awaitTerminationPeriod(shutdown.getAwaitTerminationPeriod());
            builder = builder.threadNamePrefix(properties.getThreadNamePrefix());
            builder = builder.customizers(taskSchedulerCustomizers);
            return builder;
        }

        public static ThreadPoolTaskScheduler taskScheduler(TaskSchedulerBuilder builder) {
            return builder.build();
        }
    }
}
