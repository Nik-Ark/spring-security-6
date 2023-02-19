package com.nikki.security_6.config.security.providers;

import com.nikki.security_6.config.security.authentication.CustomAuthentication;
import com.nikki.security_6.entities.User;
import com.nikki.security_6.repositories.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        int userId = customAuthentication.getUserId();
        String key = customAuthentication.getKey();

        Optional<User> user = userJpaRepository.getUserByIdAndAccessKey(userId, key);

        if (user.isPresent()) {
            System.out.println("from CustomAuthenticationProvider: " + user.get());
            return new CustomAuthentication(true, userId, null, user.get());
        } else {
            throw new BadCredentialsException("from CustomAuthenticationProvider: No Corresponding user found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
