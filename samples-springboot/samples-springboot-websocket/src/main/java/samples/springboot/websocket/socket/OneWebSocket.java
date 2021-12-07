package samples.springboot.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@ServerEndpoint(value = "/demo/one")
@Component
public class OneWebSocket extends AbsOnlineCountWebSocket {

    /**
     * 接受客户端消息
     *
     * @param message
     * @param session
     */
    @Override
    public void doOnMessage(String message, Session session) {
        super.doOnMessage(message, session);
        String respMsg = "uuid:" + UUID.randomUUID();

        responseMessage(respMsg, session);
    }

    /**
     * 响应客户端
     *
     * @param message
     * @param session
     */
    private void responseMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.info("发送客户端失败:", e);
        }
    }

}
