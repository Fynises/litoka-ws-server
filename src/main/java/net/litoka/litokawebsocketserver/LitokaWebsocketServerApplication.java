package net.litoka.litokawebsocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication(scanBasePackages = {
		"net.litoka.litokawebsocketserver.config",
		"net.litoka.litokawebsocketserver.services",
		"net.litoka.litokawebsocketserver.websocket",
		"net.litoka.litokawebsocketserver.api"
})
public class LitokaWebsocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LitokaWebsocketServerApplication.class, args);
	}

}
