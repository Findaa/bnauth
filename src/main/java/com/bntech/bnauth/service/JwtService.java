package com.bntech.bnauth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    /**
     * Generate oauth2 token for security context
     *
     * @param userDetails user mapped to security context
     * @return oauth2 token
     */
    String keygen(final UserDetails userDetails);

    /**
     * Return username encrypted in jwt token
     *
     * @param token hmac512 oauth2 token
     * @return username if token is valid
     */
    String validate(final String token);
}
