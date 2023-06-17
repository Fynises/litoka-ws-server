package net.litoka.litokawebsocketserver.exceptions;

public class ConnectionParseException extends RuntimeException {

    private final String reason;

    public ConnectionParseException(String reason) {
        this.reason = reason;
    }

}
