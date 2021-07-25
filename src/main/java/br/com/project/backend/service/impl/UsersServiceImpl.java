package br.com.project.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.backend.error.UsersNotFoundException;
import br.com.project.backend.model.Users;
import br.com.project.backend.repository.UsersRepository;
import br.com.project.backend.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUser(long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException(id));
    }

    @Override
    public Users saveUser(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public Users updateUser(long id, Users users) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException(id));

        user.setFirstname(users.getFirstname());
        user.setLastname(users.getLastname());
        user.setEmail(users.getEmail());
        user.setAge(users.getAge());
        user.setGender(users.getGender());

        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException(id));
        usersRepository.delete(user);
    }
}
