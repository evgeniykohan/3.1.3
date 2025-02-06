package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = true)
public class UserServiceDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserAndFetchRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username [" + username +
                        "] не найден в БД"));
    }
}
