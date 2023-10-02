package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.repository.UsersRepository;

import java.util.Collection;

@Service
public class UsersService {

    private final UsersRepository repository;
@Autowired
    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public Collection<Users> allUsers(){
        return repository.findAll();
    }

}
