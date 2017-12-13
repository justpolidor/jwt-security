package it.justin.jwtsecurity.domain;

public class EntityResponse {

    private Subject subject;
    private Token token;

    public EntityResponse(Subject subject, Token token) {
        this.subject = subject;
        this.token = token;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "EntityResponse{" +
                "subject=" + subject +
                ", token=" + token +
                '}';
    }
}
