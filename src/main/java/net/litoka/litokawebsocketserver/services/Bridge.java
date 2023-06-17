package net.litoka.litokawebsocketserver.services;

import net.litoka.litokawebsocketserver.dto.SocketSendRequest;
import net.litoka.litokawebsocketserver.exceptions.SendMessageException;
import net.litoka.litokawebsocketserver.websocket.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/local-api")
public class Bridge {

    private SessionManager sessionManager;

    @Autowired
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


    // NOTE: apis do not exist yet
    @PostMapping(value = "/send-to-client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatusCode> sendToClient(@RequestBody SocketSendRequest req) {
        var sessions = sessionManager.getIdManager().getSessions(req.getDestination(), req.getConnectionType());
        this.getSessions(sessions).forEach(s -> trySendMessage(s, req.getBodyString()));
        return ResponseEntity.ok().build();
    }

    private List<WebSocketSession> getSessions(Set<String> sessions) {
        return sessions.stream()
                .map(s -> sessionManager.getSession(s))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private void trySendMessage(WebSocketSession session, String message) throws SendMessageException {
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            throw new SendMessageException(e);
        }
    }

}
