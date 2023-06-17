package net.litoka.litokawebsocketserver.dto;

import lombok.EqualsAndHashCode;
import net.litoka.litokawebsocketserver.enums.ConnectionType;

/**
 * stores userId and connection type to be uses as hashmap keys
 */
@EqualsAndHashCode
public class UserKey {

    private final String userId;

    private final ConnectionType connectionType;

    public UserKey(String userId, ConnectionType connectionType) {
        this.userId = userId;
        this.connectionType = connectionType;
    }

}
