package com.socialnetwork.post.repository;

import com.socialnetwork.post.entity.Comment;
import com.socialnetwork.post.entity.Like;
import com.socialnetwork.post.entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IPostRepositoryTest {

    @Autowired
    private IPostRepository postRepository;

    private Post post;
    private Like like1;
    private Like like2;
    private Comment comment1;
    private Comment comment2;

    @BeforeEach
    void setup(){
        post = Post.builder()
                .userId(1L)
                .description("Esta es la descripción del Post")
                .image("no-image.jpg")
                .createdAt(new Date())
                .build();

        like1 = Like.builder()
                .userId(2L)
                .post(post)
                .build();
        like2 = Like.builder()
                .userId(3L)
                .post(post)
                .build();

        comment1 = Comment.builder()
                .userId(2L)
                .post(post)
                .comment("Primer comentario")
                .createdAt(new Date())
                .build();
        comment2 = Comment.builder()
                .userId(3L)
                .post(post)
                .comment("Segundo comentario")
                .createdAt(new Date())
                .build();

//        post.getLikes().add(like1);
//        post.getLikes().add(like2);
//        post.getComments().add(comment1);
//        post.getComments().add(comment2);
    }

    @Test
    void findByUserIdTest(){
        postRepository.save(post);

        List<Post> postList = postRepository.findByUserId(post.getUserId());

        assertTrue(postList.size() > 0);
        assertTrue(postList.contains(post));
        postList.forEach(p -> assertSame(p.getUserId(), post.getUserId()));
    }

    @Test
    void findByUserIdNullTest(){
        List<Post> postList = postRepository.findByUserId(-1L);
        List<Post> postList2 = postRepository.findByUserId(9999999999999999L); // En mi BBDD no existe este id.
        assertSame(postList.size(), 0);
        assertSame(postList2.size(), 0);
    }

    @Test
    void findAllByOrderByIdDesc(){
        postRepository.save(post);
        List<Post> postList = postRepository.findAllByOrderByIdDesc();

        assertTrue(postList.size() > 0);
        assertTrue(postList.contains(post));

        // Recorre la lista y compara si la fecha actual es más reciente que la siguiente
        Date actualDate = postList.get(0).getCreatedAt();
        for (int i = 0; i < postList.size() - 1; i++) {
            assertTrue(actualDate.after(postList.get(i+1).getCreatedAt()));
        }
    }

}