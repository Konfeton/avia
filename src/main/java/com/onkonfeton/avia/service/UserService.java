package com.onkonfeton.avia.service;

import com.onkonfeton.avia.model.User;
import com.onkonfeton.avia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).stream().findAny().orElse(null);
    }

    public void update(User user){
        userRepository.save(user);

    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
