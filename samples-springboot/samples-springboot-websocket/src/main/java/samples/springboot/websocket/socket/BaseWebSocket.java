package samples.springboot.websocket.socket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;

@Slf4j
public abstract class BaseWebSocket {

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        doOnOpen(session);
    }

    protected void doOnOpen(Session session) {
        log.info("onOpen");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        doOnClose(session);
    }

    protected void doOnClose(Session session) {
        log.info("onClose");
    }

    /**
     * 接受客户端消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        doOnMessage(message,session);
    }

    protected void doOnMessage(String message, Session session) {
        log.info("onMessage:{}", message);
    }


    /**
     * 发错错误的处理
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        doOnError(session,error);
    }

    protected void doOnError(Session session, Throwable error) {
        log.error("onError", error);
    }
}
