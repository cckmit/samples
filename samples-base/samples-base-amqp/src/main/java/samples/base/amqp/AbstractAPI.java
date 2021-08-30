package samples.base.amqp;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: daibin
 * @date: 2021/8/16 12:28 下午
 */
public abstract class AbstractAPI {

    static Executor executor = Executors.newCachedThreadPool();

    protected static void add(AbstractClient.Receiver receiver) {
        executor.execute(() -> {
            try {
                receiver.recv();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
