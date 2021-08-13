package over35.samples.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import over35.samples.amqp.consumer.WorkQueuesConsumer;

import java.util.HashMap;
import java.util.Map;

import static over35.samples.amqp.config.MqConstant.*;

/**
 * @author: daibin
 * @date: 2021/8/13 9:43 下午
 */
@EnableRabbit
@Configuration
public class RabbitMqConfig implements BeanFactoryAware {

    DefaultListableBeanFactory register;

    @Bean
    public Queue simpleQueue() {
        return new Queue(SIMPLE_QUEUE);
    }

    @Bean
    public Queue workQueuesQueue() {
        return new Queue(WORK_QUEUES_QUEUE);
    }

    @Bean
    public WorkQueuesConsumer workQueuesConsumer0() {
        return new WorkQueuesConsumer(0);
    }

    @Bean
    public WorkQueuesConsumer workQueuesConsumer1() {
        return new WorkQueuesConsumer(1);
    }

    @Bean
    public Queue publishSubscribeQueue1() {
        return new Queue(PUBLISH_SUBSCRIBE_QUEUE_PREFIX + 1);
    }

    @Bean
    public Queue publishSubscribeQueue2() {
        return new Queue(PUBLISH_SUBSCRIBE_QUEUE_PREFIX + 2);
    }

    @Bean
    public Queue publishSubscribeQueue3() {
        return new Queue(PUBLISH_SUBSCRIBE_QUEUE_PREFIX + 3);
    }

    @Bean
    public Exchange publishSubscribeExchange() {
        return new FanoutExchange(PUBLISH_SUBSCRIBE_EXCHANGE);
    }


    @Bean
    public Binding publishSubscribeBinding1() {
        return BindingBuilder.bind(publishSubscribeQueue1()).to(publishSubscribeExchange()).with("").noargs();
    }

    @Bean
    public Binding publishSubscribeBinding2() {
        return BindingBuilder.bind(publishSubscribeQueue2()).to(publishSubscribeExchange()).with("").noargs();
    }

    @Bean
    public Binding publishSubscribeBinding3() {
        return BindingBuilder.bind(publishSubscribeQueue3()).to(publishSubscribeExchange()).with("").noargs();
    }

    @Bean
    public Queue routingQueue1() {
        return new Queue(ROUTING_QUEUE_PREFIX + 1);
    }

    @Bean
    public Queue routingQueue2() {
        return new Queue(ROUTING_QUEUE_PREFIX + 2);
    }

    @Bean
    public Queue routingQueue3() {
        return new Queue(ROUTING_QUEUE_PREFIX + 3);
    }

    @Bean
    public Exchange routingExchange() {
        return new DirectExchange(ROUTING_EXCHANGE);
    }


    @Bean
    public Binding routingBinding1() {
        return BindingBuilder.bind(routingQueue1()).to(routingExchange()).with(R_ROUTING).noargs();
    }

    @Bean
    public Binding routingBinding2() {
        return BindingBuilder.bind(routingQueue2()).to(routingExchange()).with(R_ROUTING).noargs();
    }

    @Bean
    public Binding routingBinding3() {
        return BindingBuilder.bind(routingQueue3()).to(routingExchange()).with(R_ROUTING).noargs();
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_PREFIX + 1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_PREFIX + 2);
    }

    @Bean
    public Queue topicQueue3() {
        return new Queue(TOPIC_QUEUE_PREFIX + 3);
    }

    @Bean
    public Exchange topicExchange() {
        return new TopicExchange(TOPICS_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_ROUTING_1).noargs();
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_ROUTING_2).noargs();
    }

    @Bean
    public Binding topicBinding3() {
        return BindingBuilder.bind(topicQueue3()).to(topicExchange()).with(TOPIC_ROUTING_3).noargs();
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","direct");
        CustomExchange directExchange = new CustomExchange(DELAY_EXCHANGE,"x-delayed-message",true,false,arguments);
        directExchange.setDelayed(true);
        return directExchange;
    }

    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        register = (DefaultListableBeanFactory) beanFactory;
    }
}
