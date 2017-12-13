package it.justin.jwtsecurity.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.justin.jwtsecurity.storage.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JWTTokenIssuer {

    private static final Logger log = LoggerFactory.getLogger(JWTTokenIssuer.class);

    public static String generateToken(String signingKey, String subject) {
        log.info("generateToken with signing key:{} and subject: {}",signingKey, subject);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        String token = Jwts.builder()
                .setSubject(subject) //setexpiration
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, signingKey.getBytes())
                .compact();

        RedisUtil.INSTANCE.sadd(subject, token);
        return token;
    }

    public static String parseToken(String jwtToken, String signingKey){
        log.info("parse token with JWTToken: {} and signing Key: {}", jwtToken, signingKey);
        if(jwtToken == null) {
            return null;
        }

        String subject = Jwts.parser().setSigningKey(signingKey.getBytes()).parseClaimsJws(jwtToken).getBody().getSubject();
        if (!RedisUtil.INSTANCE.sismember(subject, jwtToken)) {
            return null;
        }

        return subject;
    }

    /*public static void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {
        RedisUtil.INSTANCE.srem(REDIS_SET_ACTIVE_SUBJECTS, (String) httpServletRequest.getAttribute("username"));
    }*/

}
