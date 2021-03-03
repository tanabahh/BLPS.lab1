package blp.lab1.service;

import blp.lab1.model.User;
import blp.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User fetchUserById(Long id){
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public User saveUser (User user) {
        userRepository.save(user);
        return user;
    }
}
