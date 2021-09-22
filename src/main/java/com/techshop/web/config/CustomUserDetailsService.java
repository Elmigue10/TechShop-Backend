package com.techshop.web.config;

import java.util.ArrayList;

import com.techshop.web.model.User;
import com.techshop.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles = null;
        if(username.equals("admin"))
        {
            roles= Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User("admin","$2a$10$0Nv/Qa7m8DdjWpBS2XRZWeP8rWDB7OdScb2grQSRDS9I9fWWlBNG2",roles);
        }
        if(username.equals("user"))
        {
            roles=Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("user","$2a$10$5VikX1NNQFL9f.N7Ta5wVuBL5HuPi7ro5Q3UZYGVOCURwiotGrVCS",roles);
        }
        throw new UsernameNotFoundException("User not found with the name "+ username);
    }*/

    public com.techshop.web.model.User save(com.techshop.web.model.User user) {
        com.techshop.web.model.User newUser = new com.techshop.web.model.User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

}