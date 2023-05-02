package com.bntech.qrekru.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.bntech.qrekru.config.Const.TOKEN_DURATION;

@Service
@Slf4j
public class JwtTokenService {
    private final Algorithm hmac512;
    private final JWTVerifier check;

    //todo: get key
//    public JwtTokenService(@Value("${getsecretsomehow???}") final String secret) {
    public JwtTokenService() {
        this.hmac512 = Algorithm.HMAC512("secret");
        this.check = JWT.require(this.hmac512).build();
    }

    public String keygen(final UserDetails userDetails) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(TOKEN_DURATION.toMillis()))
                .sign(this.hmac512);
    }

    public String validate(final String token) {
        try {
            return check.verify(token).getSubject();
        } catch (final JWTVerificationException ve) {
            log.info(ve.getMessage());
            return null;
        }
    }
}
