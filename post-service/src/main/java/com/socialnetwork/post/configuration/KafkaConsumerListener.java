package com.socialnetwork.post.configuration;

import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.service.IPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Date;

@Configuration
public class KafkaConsumerListener {

    /**
     * Escucha un TOPIC, procesa los mensajes recibidos y
     * toma acciones en función del contenido de esos mensajes
     * */

    // Log4j para imprimir el mensaje recibido
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);


    @Autowired
    private IPostService postService;

    /**
     * Esta función se encarga de procesar el mensaje recibido.
     * También puede recibir objetos
     * */
    @KafkaListener(topics = {"segundotopic"}, groupId = "noTengoGroupId")
    public void listener(String message){
        LOGGER.info("Mensaje recibido: " + message);

        // Crea un post de bienvenida con el id del usuario recibido
        Post welcomePost = Post.builder()
                .description("Hola soy nuevo en esta red social")
                .image("noimage.png")
                .createdAt(new Date())
                .userId(Long.parseLong(message))
                .build();
        postService.savePost(welcomePost);

    }

}
