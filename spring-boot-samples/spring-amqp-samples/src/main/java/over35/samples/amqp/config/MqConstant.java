package over35.samples.amqp.config;

import java.util.concurrent.TimeUnit;

/**
 * @author: daibin
 * @date: 2021/8/16 6:06 下午
 */
public class MqConstant {

    public static final String SIMPLE_QUEUE = "simple-queue";

    public static final String WORK_QUEUES_QUEUE = "work-queues";

    public static final String PUBLISH_SUBSCRIBE_QUEUE_PREFIX = "Pub-Sub-Queue-";
    public static final String PUBLISH_SUBSCRIBE_EXCHANGE = "Pub-Sub-Exchange";

    public static final String ROUTING_EXCHANGE = "Routing-Exchange";
    public static final String R_ROUTING = "R-Routing";
    public static final String ROUTING_QUEUE_PREFIX = "Routing-Queue-";

    public static final String TOPICS_EXCHANGE = "Topics-Exchange";
    public static final String TOPIC_QUEUE_PREFIX = "Topics-";
    public static final String TOPIC_ROUTING_1 = "route";
    public static final String TOPIC_ROUTING_2 = "route.*";
    public static final String TOPIC_ROUTING_3 = "route.#";


    public static final String DELAY_QUEUE = "delay_queue";
    public static final String DELAY_EXCHANGE = "delay_exchange";
    public static final String DELAY_ROUTING_KEY = "delay_routingKey";

    public static final int DELAY_TIME = (int) TimeUnit.SECONDS.toMillis(30);
}
