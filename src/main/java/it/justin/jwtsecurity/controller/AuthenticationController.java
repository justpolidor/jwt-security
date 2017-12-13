package it.justin.jwtsecurity.controller;

import it.justin.jwtsecurity.domain.Subject;
import it.justin.jwtsecurity.domain.Token;
import it.justin.jwtsecurity.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final String signingKey = "prova";
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/issue-token")
    public String issueToken(@RequestBody Subject subject) {
        log.info(subject.toString());
        String token = authenticationService.generateToken(signingKey, subject.getSubject());
        log.info("Issue token returned a new token:{}", token);
        return token;
    }

    @PostMapping("/check-token")
    public String parseToken(@RequestBody Token token) {
        log.info(token.toString());
        String subject = authenticationService.parseToken(token.getToken(), signingKey);
        log.info("Parse token returned {}", subject);
        return subject;
    }

}
