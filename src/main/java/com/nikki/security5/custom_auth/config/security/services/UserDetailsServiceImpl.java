package com.nikki.security5.custom_auth.config.security.services;

import com.nikki.security5.custom_auth.config.security.authorities.SecurityUser;
import com.nikki.security5.custom_auth.entities.User;
import com.nikki.security5.custom_auth.repositories.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userJpaRepository.findUserByUsername(username);

        if (user.isPresent()) {
            System.out.println("from UserDetailsServiceImpl: " + user);
        }

        return user.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found" + username));
    }
}
