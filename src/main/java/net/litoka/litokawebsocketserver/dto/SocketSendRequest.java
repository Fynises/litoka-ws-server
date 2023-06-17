package net.litoka.litokawebsocketserver.dto;

import lombok.Data;
import net.litoka.litokawebsocketserver.enums.ConnectionType;

@Data
public class SocketSendRequest {

    private String destination;
    private String connectionType;
    private String bodyString;

    public ConnectionType getConnectionType() {
        return switch (connectionType) {
            case "so" -> ConnectionType.SHOUTOUT;
            case "cplayer" -> ConnectionType.CLIP_PLAYER;
            default -> throw new RuntimeException("connection type: %s not found".formatted(connectionType));
        };
    }


}
