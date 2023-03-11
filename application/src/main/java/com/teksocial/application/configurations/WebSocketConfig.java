package com.teksocial.application.configurations;

import com.teksocial.application.handler.ChatWebsocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
public  class WebSocketConfig implements WebSocketConfigurer {

    private static  final String CHAT_ENDPOINT= "/chat";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(websocketHandler(), CHAT_ENDPOINT).setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler websocketHandler(){
        return new ChatWebsocketHandler();
    }
}

