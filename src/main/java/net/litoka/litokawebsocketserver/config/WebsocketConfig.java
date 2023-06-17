package net.litoka.litokawebsocketserver.config;

import lombok.extern.slf4j.Slf4j;
import net.litoka.litokawebsocketserver.api.ShoutoutConfigApi;
import net.litoka.litokawebsocketserver.enums.ConnectionType;
import net.litoka.litokawebsocketserver.websocket.SessionManager;
import net.litoka.litokawebsocketserver.websocket.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@Configuration
@EnableWebSocket
@Controller
public class WebsocketConfig implements WebSocketConfigurer {

    private SessionManager sessionManager;

    private ShoutoutConfigApi shoutoutConfigApi;

    @Value("${application.websocket.allowed-origin}")
    private String origin;

    @Autowired
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Autowired
    public void setShoutoutConfigApi(ShoutoutConfigApi api) {
        this.shoutoutConfigApi = api;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("registering websocket handler");
        registry.addHandler(makeShoutoutHandler(), "/ws/shoutout")
                .setAllowedOrigins(origin);
    }

    private SocketHandler makeShoutoutHandler() {
        return new SocketHandler(sessionManager, ConnectionType.SHOUTOUT, shoutoutConfigApi);
    }

}
