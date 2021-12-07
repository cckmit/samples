package samples.springboot.websocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@ServerEndpoint(value = "/demo/oneToOne")
@Component
public class OneToOneWebSocket extends AbsOnlineCountWebSocket {

    /**
     * @param message id-text
     * @param session
     */
    @Override
    public void doOnMessage(String message, Session session) {
        super.doOnMessage(message, session);

        if (message.length() == 0 || !StringUtils.contains(message, "-")) {
            return;
        }
        String id = StringUtils.substringBefore(message, "-");
        String text = StringUtils.substringAfter(message, "-");

        Session toSession = getClients().get(id);

        responseMessage(text, toSession);
    }

    /**
     * 一对一发信息
     *
     * @param message
     * @param session
     */
    private void responseMessage(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
