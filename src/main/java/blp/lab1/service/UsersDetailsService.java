package blp.lab1.service;

import blp.lab1.model.Role;
import blp.lab1.model.User;
import blp.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UsersDetailsService implements UserDetailsService {
    String ROLE_PREFIX = "ROLE_";

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // System.out.println("Username\n");
        User user = repository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<Role> roles = user.getRoles();
        String role = roles.iterator().next().getName();
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), authorities);
    }
}
