package com.revature.effective.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.effective.config.QPGroupConst;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
    
    private WebClient defaultWebClient;
	
	@PostConstruct
	private void initWebClient() {
		defaultWebClient = WebClient.create()
			.mutate()
			.clientConnector(getConnector())
			.defaultHeaders(headers -> headers.addAll(getDefaultHeaders()))
			.build();
	}
	
	@Bean("defaultWebClient")
	public WebClient defaultWebClient() {
		return defaultWebClient;
	}
	
	private HttpHeaders getDefaultHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE);
		headers.add(HttpHeaders.AUTHORIZATION, "NONE");
		return headers;
	}
	
	private ClientHttpConnector getConnector() {
		
		HttpClient client = HttpClient.create()
			.tcpConfiguration(tcpConfig -> {
				tcpConfig.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,QPGroupConst.CONNECT_TIMEOUT_MILLIS);
				tcpConfig.doOnConnected(connection -> {
					connection.addHandlerLast("ReadTimoutHandler", 
							new ReadTimeoutHandler(QPGroupConst.READ_TIMEOUT_SECONDS));
					connection.addHandlerLast("WriteTimeoutHandler", 
							new WriteTimeoutHandler(QPGroupConst.WRITE_TIMEOUT_SECONDS));
				});
				return tcpConfig;
			});

		return new ReactorClientHttpConnector(client);
	}
}
