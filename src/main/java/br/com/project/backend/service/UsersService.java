package br.com.project.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.backend.model.Users;

@Service
public interface UsersService {

    List<Users> getAllUsers();

    Users getUser(long id);

    Users saveUser(Users users);

    Users updateUser(long id, Users users);

    void deleteUser(long id);
}
