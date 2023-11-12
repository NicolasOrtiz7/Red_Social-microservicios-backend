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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private IPostRepository postRepository;
    @Mock
    private UserClient userClient;
    @InjectMocks
    private PostServiceImpl postService;

    // ---------------------------------------

    private final Long postId = 1L;
    private final Long userId = 1L;

    private User user1;
    private User user2;

    private Post post1;
    private Post post2;
    private Post post3;
    private Post post4;

    private List<Post> mockList;

    // ---------------------------------------


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
        assertThrows(NotFoundException.class, ()-> postService.findPostById(any()));
        verify(postRepository, times(1)).findById(any());
    }
    @Test
    void findPostById_UserIdDoesNotExists(){
        when(userClient.findUserById(any()))
                .thenThrow(FeignException.class);
        assertThrows(FeignException.class, ()-> userClient.findUserById(any()));
    }

    @Test
    void savePost() {
        when(postRepository.save(any()))
                .thenReturn(post1);
        Post postSaved = postService.savePost(post1);
        assertSame(post1, postSaved);
    }

    @Test
    @Disabled("No está implementada la función para editar Posts")
    void updatePost() {
    }

    @Test
    void deletePost() {
        when(postRepository.findById(postId))
                .thenReturn(Optional.of(post1));

        Post postDeleted = postService.deletePost(postId);
        assertSame(post1, postDeleted);

        when(postRepository.findById(postId))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> postService.findPostById(postId));

        verify(postRepository, times(2)).findById(postId);
        verify(postRepository, times(1)).deleteById(postId);
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
            for (Post post : postList) {
                Long actualUserId = post.getUserId();
                post.setUserOwner(new User(actualUserId));
            }

            assertSame(4, postList.size());
            for (Post p: postList) assertNotNull(p.getUserOwner());

            verify(postRepository, times(1)).findAllByOrderByIdDesc();
            verify(userClient, times(postList.size())).findUserById(any());
        }
        @Test
        void findAll_IfAnyUserDoesNotExist(){
            when(postRepository.findAllByOrderByIdDesc())
                    .thenReturn(mockList);
            List<Post> allPosts = postService.findAll();

            assertSame(4, allPosts.size());
            verify(userClient, times(allPosts.size())).findUserById(any());

            // Asigna un userOwner a cada Post
            for (Post p : allPosts){
                if (p.getUserId() != 2L){
                    p.setUserOwner(new User(p.getUserId()));
                }
            }
            allPosts = allPosts.stream().filter(p -> p.getUserOwner() != null)
                    .collect(Collectors.toList());

            assertSame(2, allPosts.size());
            verify(postRepository, times(1)).findAllByOrderByIdDesc();

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

            assertThrows(FeignException.class, ()-> userClient.findUserById(any()));
            assertThrows(NotFoundException.class, ()-> postRepository.findByUserId(any()));

            verify(postRepository, times(1)).findByUserId(any());
            verify(userClient, times(1)).findUserById(any());

        }

    }

}