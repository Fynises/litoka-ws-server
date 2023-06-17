package net.litoka.litokawebsocketserver.dto;

import lombok.Data;
import net.litoka.litokawebsocketserver.exceptions.ConnectionParseException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Data
public class ConnectionParams {

    private String connectionId;

    public ConnectionParams(URI sessionUri) throws ConnectionParseException {
        var queryMap = getMap(sessionUri);
        this.connectionId = Optional.ofNullable(queryMap.getFirst("id"))
                .orElseThrow(() -> new ConnectionParseException(""));
    }

    private static MultiValueMap<String, String> getMap(URI uri) {
        return UriComponentsBuilder.fromUri(uri)
                .build()
                .getQueryParams();
    }

}
