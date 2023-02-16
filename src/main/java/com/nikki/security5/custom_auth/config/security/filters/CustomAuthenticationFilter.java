package com.nikki.security5.custom_auth.config.security.filters;

import com.nikki.security5.custom_auth.config.security.authentication.CustomAuthentication;
import com.nikki.security5.custom_auth.config.security.managers.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {

        String key = request.getHeader("key");
        int userId = request.getIntHeader("userId");

        Authentication authentication = new CustomAuthentication(
                false, userId, key, null
        );

        try {
            authentication = customAuthenticationManager.authenticate(authentication);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.sendError(403, "Bad authorization credentials");
        }

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
