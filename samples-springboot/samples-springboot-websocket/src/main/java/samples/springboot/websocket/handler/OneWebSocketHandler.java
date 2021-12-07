package samples.springboot.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class OneWebSocketHandler extends TextWebSocketHandler {

    /**
     * 连接建立后
     *
     * @param session
     * @throws Exception
     * @OnOpen
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("{}连接建立", session.getId());
    }

    /**
     * 连接关闭后
     *
     * @param session
     * @param status
     * @throws Exception
     * @OnClose
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("{}连接关闭", session.getId());
    }

    /**
     * @param session
     * @param message
     * @throws Exception
     * @OnMessage
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("message.payload:{}", payload);
        session.sendMessage(message);
    }
}
