package com.nikki.security5.custom_auth.config.security.filters;

import com.nikki.security5.custom_auth.config.security.authentication.CustomAuthentication;
import com.nikki.security5.custom_auth.config.security.managers.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        String accessKey = request.getHeader("access-key");

        if (accessKey == null) {
            filterChain.doFilter(request, response);
            return;
        }

        int userId;

        try {
            userId = request.getIntHeader("userId");
        } catch (NumberFormatException ex) {
            System.out.println("userId should be int.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        Authentication authentication = new CustomAuthentication(
                false, userId, accessKey, null
        );

        try {
            authentication = customAuthenticationManager.authenticate(authentication);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
