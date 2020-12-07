package br.com.project.backend.controller;

import br.com.project.backend.persistence.model.Users;
import br.com.project.backend.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static br.com.project.backend.utils.Utils.asJsonString;
import static br.com.project.backend.utils.Utils.getUsers;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTests {

    private final long id = 1;
    private final Users user = getUsers();
    @MockBean
    private UsersRepository usersRepository;
    @Autowired
    private MockMvc mock;

    @Test
    public void whenGetUsers_thenReturnJsonArray() throws Exception {
        when(usersRepository.findAll()).thenReturn(Collections.singletonList(user));
        mock.perform(get("/api/v1/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)));
    }

    @Test
    public void whenGetUsers_thenReturnEmptyArray() throws Exception {
        when(usersRepository.findAll()).thenReturn(Collections.emptyList());
        mock.perform(get("/api/v1/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(0)));
    }

    @Test
    public void whenGetUser_thenReturnUser() throws Exception {
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));
        mock.perform(get("/api/v1/user/" + id)).andExpect(status().isOk())
                .andExpect((jsonPath("$.firstname", is(user.getFirstname()))));
    }

    @Test
    public void whenGetUser_thenReturnUsersNotFoundException() throws Exception {
        when(usersRepository.findById(id)).thenReturn(Optional.empty());
        mock.perform(get("/api/v1/user/" + id)).andExpect(status().isNotFound());
    }

    @Test
    public void whenPostUser_thenReturnUser() throws Exception {
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        mock.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.firstname", is(user.getFirstname()))));
    }

    @Test
    public void whenPutUser_thenReturnUser() throws Exception {
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        mock.perform(put("/api/v1/user/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.firstname", is(user.getFirstname()))));
    }

    @Test
    public void whenPutUser_thenReturnUsersNotFoundException() throws Exception {
        when(usersRepository.findById(id)).thenReturn(Optional.empty());

        mock.perform(put("/api/v1/user/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenDeleteUser_thenReturnSuccess() throws Exception {
        when(usersRepository.findById(id)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        mock.perform(delete("/api/v1/user/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteUser_thenReturnUsersNotFoundException() throws Exception {
        when(usersRepository.findById(id)).thenReturn(Optional.empty());

        mock.perform(delete("/api/v1/user/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isNotFound());
    }
}
