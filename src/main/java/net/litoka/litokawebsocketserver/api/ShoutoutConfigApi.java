package net.litoka.litokawebsocketserver.api;

import net.litoka.litokawebsocketserver.dto.ShoutoutConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShoutoutConfigApi {

    private WebClient webClient;

    @Autowired
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ShoutoutConfig> getShoutoutConfig(String configId) {
        return webClient.get()
                .uri(b -> b.path("/local-api/shoutout-config/get").queryParam("config_id", configId).build())
                .retrieve()
                .bodyToMono(ShoutoutConfig.class);
    }

}
