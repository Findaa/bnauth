package com.bntech.bnauth.api;

import com.bntech.bnauth.data.object.AppUserDetails;
import com.bntech.bnauth.service.JwtService;
import com.bntech.bnauth.service.impl.JwtServiceImpl;
import com.bntech.bnauth.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.bntech.bnauth.config.Const.BEARER;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtService jwt;
    private final UserDetailsServiceImpl users;

    @Autowired
    public JwtRequestFilter(JwtServiceImpl jwt, UserDetailsServiceImpl users) {
        this.jwt = jwt;
        this.users = users;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain
    ) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        final String username = jwt.validate(token);
        if (username == null) {
            chain.doFilter(request, response);
            return;
        }

        final AppUserDetails userDetails = users.loadUserByUsername(username);
        if (userDetails == null) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
