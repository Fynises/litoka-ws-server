package net.litoka.litokawebsocketserver.websocket;

import net.litoka.litokawebsocketserver.dto.IdManager;
import net.litoka.litokawebsocketserver.dto.ShoutoutConfig;
import net.litoka.litokawebsocketserver.enums.ConnectionType;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * maintains a cache of current websocket sessions
 */
@Configuration
public class SessionManager {

    private final ConcurrentMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    private final IdManager idManager = new IdManager();


    public Optional<WebSocketSession> getSession(String id) {
        return Optional.ofNullable(sessionMap.get(id));
    }

    public void registerSession(WebSocketSession session, ConnectionType type, ShoutoutConfig config) {
        idManager.setSession(config.getUserId(), type, session.getId());
        sessionMap.put(session.getId(), session);
    }

    public void removeSession(WebSocketSession session) {
        idManager.removeSession(session.getId());
        sessionMap.remove(session.getId());
    }

    public IdManager getIdManager() {
        return this.idManager;
    }

}
