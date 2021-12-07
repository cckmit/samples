package samples.springboot.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import samples.springboot.websocket.handler.OneWebSocketHandler;


@Configuration
@EnableWebSocket
public class CustomWebSocketConfigurer implements WebSocketConfigurer {

    @Autowired
    OneWebSocketHandler oneWebSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(oneWebSocketHandler,"/handler/one")
                .setAllowedOrigins("*");
    }
}
