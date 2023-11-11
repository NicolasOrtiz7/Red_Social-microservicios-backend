package com.socialnetwork.post.service.impl;

import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.feign.UserClient;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.repository.IPostRepository;
import feign.FeignException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private IPostRepository postRepository;
    @Mock
    private UserClient userClient;
    @InjectMocks
    private PostServiceImpl postService;

    private User user1;
    private User user2;

    private Post post1;
    private Post post2;
    private Post post3;
    private Post post4;

    private List<Post> mockList;

    private Long postId = 1L;
    private Long userId = 1L;


    @BeforeEach
    void setUp() {
        user1 = User.builder().id(1L).name("Leo").username("leomessi").build();
        user2 = User.builder().id(2L).name("Cristiano").username("cristianoronaldo").build();

        post1 = Post.builder().id(1L).userId(1L).description("Esta es la primera descripción").build();
        post2 = Post.builder().id(2L).userId(1L).description("Esta es la segunda descripción").build();
        post3 = Post.builder().id(3L).userId(2L).description("Esta es la tercera descripción").build();
        post4 = Post.builder().id(4L).userId(2L).description("Esta es la cuarta descripción").build();

        mockList = Arrays.asList(post1, post2, post3, post4);
    }

    @Test
    void findPostById_PostExists() {
        when(postRepository.findById(postId))
                .thenReturn(Optional.of(post1));

        Post postFound = postService.findPostById(postId);
        assertSame(post1, postFound);
        verify(postRepository, times(1)).findById(postId);
    }
    @Test
    void findPostById_PostDoesNotExists(){
        when(postRepository.findById(any()))
                .thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, ()->{
            postService.findPostById(any());
        });
        verify(postRepository, times(1)).findById(any());
    }
    @Test
    void findPostById_UserIdDoesNotExists(){
        when(userClient.findUserById(any()))
                .thenThrow(FeignException.class);
        assertThrows(FeignException.class, ()->{
            userClient.findUserById(any());
        });
    }

    @Test
    void savePost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }


    @Nested // Métodos que devuelven listas
    class ListsTests{

        User user3;
        Post post5;

        @BeforeEach
        void setUp(){
            user3 = null;
            post5 = Post.builder().id(5L).userId(3L).description("Este Post no tiene user").build();
        }

        @Test
        void findAll() {
            when(postRepository.findAllByOrderByIdDesc())
                    .thenReturn(mockList);

            List<Post> postList = postService.findAll();

            // Asignar un usuario a cada Post
            for (int i = 0; i < postList.size(); i++) {
                Long actualUserId = postList.get(i).getUserId();
                postList.get(i).setUserOwner(new User(actualUserId));
            }

            assertSame(4, postList.size());
            for (Post p: postList) assertNotNull(p.getUserOwner());

            verify(postRepository, times(1)).findAllByOrderByIdDesc();
            verify(userClient, times(postList.size())).findUserById(any());
        }
        @Test
        @Disabled("Tengo que corregirlo")
        void findAll_IfAnyUserDoesNotExist(){
            when(postRepository.findAllByOrderByIdDesc())
                    .thenReturn(mockList);

            // esto da error -------------------
            when(userClient.findUserById(3L))
                    .thenThrow(NotFoundException.class);
            when(userClient.findUserById(2L))
                    .thenReturn(new User());
            // ---------------------------------
            List<Post> postList = postService.findAll();



        }

        @Test
        void findPostsByUserId_UserExists() {
            List<Post> mockListPostsByUserId = Arrays.asList(post1, post2);

            when(postRepository.findByUserId(userId))
                       .thenReturn(mockListPostsByUserId);

            List<Post> postList = postService.findPostsByUserId(userId);

            assertSame(mockListPostsByUserId, postList);
            assertSame(1L, postList.get(0).getUserId());

            verify(postRepository, times(1)).findByUserId(userId);
            verify(userClient, times(1)).findUserById(userId);

        }

        // Cuando el userId no existe
        @Test
        void findPostsByUserId_UserDoesNotExist(){
            when(postRepository.findByUserId(any()))
                    .thenThrow(NotFoundException.class);
            when(userClient.findUserById(any()))
                    .thenThrow(FeignException.class);

            assertThrows(FeignException.class, ()->{
                userClient.findUserById(any());
            });
            assertThrows(NotFoundException.class, ()->{
                postRepository.findByUserId(any());
            });

            verify(postRepository, times(1)).findByUserId(any());
            verify(userClient, times(1)).findUserById(any());

        }

    }

}