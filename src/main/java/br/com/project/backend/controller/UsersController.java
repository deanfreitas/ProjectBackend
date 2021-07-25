package br.com.project.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.backend.model.Users;
import br.com.project.backend.service.UsersService;

@RestController
@RequestMapping(value = "/api/v1")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable("id") long id) {
        return usersService.getUser(id);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Users saveUser(@RequestBody Users users) {
        return usersService.saveUser(users);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Users updateUser(@PathVariable("id") long id, @RequestBody Users users) {
        return usersService.updateUser(id, users);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") long id) {
        usersService.deleteUser(id);
    }

}
