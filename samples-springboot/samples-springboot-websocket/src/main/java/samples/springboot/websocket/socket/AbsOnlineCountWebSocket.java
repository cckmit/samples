package samples.springboot.websocket.socket;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public abstract class AbsOnlineCountWebSocket extends BaseWebSocket {

    /**
     * 记录当前在线连接数
     */
    @Getter
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    @Getter
    private static final Map<String, Session> clients = new ConcurrentHashMap<>();

    @Override
    public void doOnOpen(Session session) {
        super.doOnOpen(session);
        clients.put(session.getId(), session);
        int onlineCountNum = onlineCount.incrementAndGet();
        log.info("{}加入,在线用户：{}", session.getId(), onlineCountNum);
    }

    @Override
    public void doOnClose(Session session) {
        super.doOnClose(session);
        clients.remove(session.getId());
        int onlineCountNum = onlineCount.decrementAndGet();
        log.info("{}离线,在线用户：{}", session.getId(), onlineCountNum);
    }
}
