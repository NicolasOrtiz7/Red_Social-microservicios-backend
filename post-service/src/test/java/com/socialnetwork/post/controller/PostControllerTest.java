package com.socialnetwork.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialnetwork.post.entity.Post;
import com.socialnetwork.post.exception.NotFoundException;
import com.socialnetwork.post.model.User;
import com.socialnetwork.post.service.ICommentService;
import com.socialnetwork.post.service.ILikeService;
import com.socialnetwork.post.service.IPostService;
import jakarta.ws.rs.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IPostService postService;
    @MockBean
    private ILikeService likeService;
    @MockBean
    private ICommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    private String API_PATH = "/posts";

    private final Long postId = 1L;
    private final Long userId = 1L;

    private User user1;
    private User user2;

    private Post post1;
    private Post post2;
    private Post post3;
    private Post post4;

    private List<Post> mockList;

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
    void findAllTest() throws Exception {
        when(postService.findAll())
                .thenReturn(mockList);

        mockMvc.perform(get(API_PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(post1.getDescription()))
                .andExpect(jsonPath("$[1].description").value(post2.getDescription()));

        verify(postService, times(1)).findAll();
    }

    @Test
    void findPostsByUserId() throws Exception {
        String pathUrl = API_PATH + "/user/{userId}";
        List<Post> mockListByUserId = Arrays.asList(post1, post2);
        when(postService.findPostsByUserId(any()))
                .thenReturn(mockListByUserId);

        mockMvc.perform(get(pathUrl, user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(post1.getDescription()))
                .andExpect(jsonPath("$[1].description").value(post2.getDescription()));

        verify(postService, times(1)).findPostsByUserId(any());
    }
    @Test
    void findPostsByUserId_UserDoesNotExist() throws Exception {
        String pathUrl = API_PATH + "/user/{userId}";
        when(postService.findPostsByUserId(user1.getId()))
                .thenThrow(NotFoundException.class);

        mockMvc.perform(get(pathUrl, user1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(postService, times(1)).findPostsByUserId(any());
    }

    @Test
    void findPostByPostId() throws Exception {
        String pathUrl = API_PATH + "/post/{postId}";
        when(postService.findPostById(postId))
                .thenReturn(post1);

        mockMvc.perform(get(pathUrl, postId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(post1.getDescription()));

        verify(postService, times(1)).findPostById(postId);
    }
    @Test
    void findPostByPostId_PostDoesNotExist() throws Exception {
        String pathUrl = API_PATH + "/post/{postId}";
        when(postService.findPostById(postId))
                .thenThrow(NotFoundException.class);

        mockMvc.perform(get(pathUrl, postId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(postService, times(1)).findPostById(postId);
    }

    @Test
    void savePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void findLikesByPostId() {
    }

    @Test
    void sendLike() {
    }

    @Test
    void findCommentsByPostId() {
    }

    @Test
    void sendComments() {
    }
}