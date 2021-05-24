package io.hala.whistleon.service.user;

public interface AuthService {
    void authEmail(String to);

    void authCode(String email, String code);

    long getCodeId(String email);

    void deleteCode(long id);

}
