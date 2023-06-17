package net.litoka.litokawebsocketserver.enums;

public enum ConnectionType {
    SHOUTOUT("so"),
    CLIP_PLAYER("cplayer");

    public final String value;

    private ConnectionType(String value) {
        this.value = value;
    }
}
