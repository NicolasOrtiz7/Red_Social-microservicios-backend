package com.socialnetwork.profile.service;

import com.socialnetwork.profile.dto.UserDTO;
import com.socialnetwork.profile.entity.User;
import com.socialnetwork.profile.exception.NotFoundException;
import com.socialnetwork.profile.feign.ChatClient;
import com.socialnetwork.profile.feign.PostClient;
import com.socialnetwork.profile.model.Chat;
import com.socialnetwork.profile.model.Message;
import com.socialnetwork.profile.model.Post;
import com.socialnetwork.profile.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService, PostClient, ChatClient {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PostClient postClient;

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {

        User userFound = userRepository.findById(id).orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        user.setId(id);

        // Copia las propiedades del usuario-actualizado al usuario-existente
        // Sirve para evitar que se reinicien algunos valores que vienen por defecto al crear un usuario
        BeanUtils.copyProperties(user, userFound,
                "id", "followers");

        return userRepository.save(userFound);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    // ------------ Seguidores ------------

    // Obtiene los seguidores del usuario
    @Override
    public List<UserDTO> findFollowersByUserId(Long userId) {

        List<User> followers =  userRepository.findFollowersByUserId(userId);
        List<UserDTO> followersDTO = new ArrayList<>();

        // Mapea los User a UserDTO
        return followers.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    // Obtiene los seguidos del usuario
    @Override
    public List<UserDTO> findUsersByFollowerId(Long followerId) {

        List<User> followed = userRepository.findUsersByFollowerId(followerId);

        List<UserDTO> followedDTO = new ArrayList<>();

        return followed.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    // Seguir a un usuario
    @Override
    public User followUser(Long user_id, Long follower_id) {

        User user = userRepository.findById(user_id).orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        User userFollower = userRepository.findById(follower_id).orElseThrow(()-> new NotFoundException("Usuario no encontrado"));

        Set<User> userFollowersList = user.getFollowers();

        userFollowersList.add(userFollower);
        userRepository.save(user);

        return user;
    }

    // Dejar de seguir a un usuario
    @Override
    public User unfollowUser(Long user_id, Long unfollowed_id) {

        User user = userRepository.findById(user_id).orElseThrow();
        User userFollower = userRepository.findById(unfollowed_id).orElseThrow();

        Set<User> userFollowersList = user.getFollowers();

        userFollowersList.remove(userFollower);
        userRepository.save(user);

        return user;
    }

    // ------------ Post Feign Client ------------

    public List<Post> findAllPosts(){
        return postClient.findAllPosts();
    }

    @Override
    public List<Post> findPostsByUserId(Long id) {
        return postClient.findPostsByUserId(id);
    }

    @Override
    @DeleteMapping("/delete/{post_id}")
    public void deletePost(@PathVariable Long post_id){
        postClient.deletePost(post_id);
    }

    // ------------ Chat Feign Client ------------

    @Override
    public List<Message> findMessagesByChatId(Long chatId) {
        return chatClient.findMessagesByChatId(chatId);
    }

    @Override
    public List<Chat> findChatsByUserId(Long userId) {
        return chatClient.findChatsByUserId(userId);
    }
}
