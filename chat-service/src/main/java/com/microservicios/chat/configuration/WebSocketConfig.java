package com.microservicios.chat.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registra el endpoint WebSocket.
        // SockJS permite que la comunicación funcione en navegadores que no son compatibles con WebSocket puro
        registry.addEndpoint("/websocket-endpoint")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Habilita un broker para enviar mensajes a los clientes suscritos a "/topic"
        registry.enableSimpleBroker("/topic");

        // Establece el prefijo para las rutas de destino de los mensajes. Es como el @RequestMapping
        registry.setApplicationDestinationPrefixes("/app");
    }
}
