package samples.springboot.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.UUID;

@Slf4j
@ServerEndpoint(value = "/demo/oneToMany")
@Component
public class OneToManyWebSocket extends AbsOnlineCountWebSocket {

    @Override
    public void doOnMessage(String message, Session session) {
        super.doOnMessage(message, session);
        String respMsg = "uuid:" + UUID.randomUUID();
        responseMessage(respMsg, session);
    }

    /**
     * 群发
     * 所有在线的客户端都可以收到
     * @param message
     * @param session
     */
    private void responseMessage(String message, Session session) {
        getClients().entrySet().stream().forEach(s -> {
            s.getValue().getAsyncRemote().sendText(message);
        });
    }
}
