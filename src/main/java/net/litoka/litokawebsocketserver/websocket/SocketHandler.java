package net.litoka.litokawebsocketserver.websocket;

import lombok.extern.slf4j.Slf4j;
import net.litoka.litokawebsocketserver.api.ShoutoutConfigApi;
import net.litoka.litokawebsocketserver.dto.ConnectionParams;
import net.litoka.litokawebsocketserver.enums.ConnectionType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class SocketHandler extends TextWebSocketHandler {

    private final SessionManager sessionManager;

    private final ConnectionType connectionType;

    private final ShoutoutConfigApi api;

    public SocketHandler(
            SessionManager sessionManager,
            ConnectionType connectionType,
            ShoutoutConfigApi api
    ) {
        this.sessionManager = sessionManager;
        this.connectionType = connectionType;
        this.api = api;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("connection established");
        var connectionParams = new ConnectionParams(session.getUri());
        api.getShoutoutConfig(connectionParams.getConnectionId()).subscribe(res -> {
            sessionManager.registerSession(session, connectionType, res);
            log.info("successfully registered {}", session.getId());
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionManager.removeSession(session);
        log.info("successfully removed session");
    }

}
