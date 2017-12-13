package it.justin.jwtsecurity.service;

import it.justin.jwtsecurity.security.JWTTokenIssuer;
import it.justin.jwtsecurity.storage.RedisUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public String generateToken(String signingKey, String subject) {
        if(!RedisUtil.INSTANCE.sexists(subject)) {
            return JWTTokenIssuer.generateToken(signingKey, subject);
        }

        return null;
    }

    public String parseToken(String jwtToken, String signingKey) {
        return JWTTokenIssuer.parseToken(jwtToken, signingKey);
    }
}
