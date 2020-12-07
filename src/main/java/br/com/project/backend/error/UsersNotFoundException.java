package br.com.project.backend.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsersNotFoundException extends RuntimeException {

    public UsersNotFoundException(long id) {
        super("Could not find user " + id);
    }
}
