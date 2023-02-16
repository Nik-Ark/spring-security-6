package com.nikki.security5.custom_auth.config.security.authentication;

import com.nikki.security5.custom_auth.config.security.authorities.SecurityAuthority;
import com.nikki.security5.custom_auth.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomAuthentication implements Authentication {

    private final boolean authenticated;
    private final int userId;
    private final String key;
    private final User user;

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream()
                .map(SecurityAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return user.getAccessKey();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public int getUserId() {
        return userId;
    }

    public String getKey() {
        return key;
    }

    // This method is defined as default and can be omitted here as it is implemented in the interface.
    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
