package br.com.project.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.project.backend.enums.Gender;
import br.com.project.backend.model.Users;

public class Utils {

    public static Users getUsers() {
        Users user = new Users();

        user.setFirstname("Louise");
        user.setLastname("Brown");
        user.setEmail("Brown@gmail.com");
        user.setAge(18);
        user.setGender(Gender.MASCULINO);

        return user;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
