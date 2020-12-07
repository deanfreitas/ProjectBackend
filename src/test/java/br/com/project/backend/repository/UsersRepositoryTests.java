package br.com.project.backend.repository;

import br.com.project.backend.persistence.model.Users;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static br.com.project.backend.utils.Utils.getUsers;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsersRepositoryTests {

    @Autowired
    private UsersRepository usersRepository;

    @Before
    public void prepareToTests() {
        usersRepository.save(getUsers());
    }

    @Test
    public void testGetAllUser_whenGetEntity_thenOK() {
        usersRepository.save(getUsers());
        Assert.assertEquals(2, usersRepository.findAll().size());
    }

    @Test
    public void testGetUser_whenGetEntity_thenOK() {
        long id = 1;
        Optional<Users> user = usersRepository.findById(id);
        Assert.assertNotNull(user);
    }

    @Test
    public void testGetUser_whenGetEntity_thenNullFound() {
        long id = 80;
        Optional<Users> user = usersRepository.findById(id);
        Assert.assertNotNull(user);
    }

    @Test
    public void testSaveUser_whenSaveEntity_thenOK() {
        Users user = usersRepository.save(getUsers());
        Assert.assertEquals(user.getFirstname(), getUsers().getFirstname());
    }

    @Test
    public void testDeleteUser_whenDeleteEntity_thenOK() {
        Users user = usersRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
        usersRepository.delete(user);

        Assert.assertNull(usersRepository.findById(user.getId()).orElse(null));
    }

    @After
    public void finalizedTest() {
        usersRepository.deleteAll();
    }
}
