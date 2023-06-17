package net.litoka.litokawebsocketserver.services;

import net.litoka.litokawebsocketserver.websocket.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class Monitoring {

    // TODO:
    private SessionManager sessionManager;

    @Autowired
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping(value = "/get-session-for-user")
    public ResponseEntity<?> getSessionsForUser() {
        return ResponseEntity.ok("Not implemented");
    }

}
