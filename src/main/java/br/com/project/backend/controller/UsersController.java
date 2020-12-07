package br.com.project.backend.controller;

import br.com.project.backend.error.UsersNotFoundException;
import br.com.project.backend.persistence.model.Users;
import br.com.project.backend.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable("id") long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException(id));
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Users saveUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @PutMapping("/user/{id}")
    public Users updateUser(@PathVariable("id") long id, @RequestBody Users users) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException(id));

        user.setFirstname(users.getFirstname());
        user.setLastname(users.getLastname());
        user.setEmail(users.getEmail());
        user.setAge(users.getAge());
        user.setGender(users.getGender());

        return usersRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new UsersNotFoundException(id));
        usersRepository.delete(user);
    }

}
