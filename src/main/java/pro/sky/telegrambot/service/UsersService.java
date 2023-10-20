package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.User;
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

    public Collection<Users> allUsers() {
        return repository.findAll();
    }

    public Users findOrSaveUsers(User telegramUser) {
        Users persistentUser = repository.findUsersByTelegramUserId(telegramUser.id());
        if (persistentUser == null) {
            Users transientUser = new Users();
            transientUser.setTelegramUserId(telegramUser.id());
            transientUser.setFirstName(telegramUser.firstName());
            transientUser.setLastName(telegramUser.lastName());
            return repository.save(transientUser);
        }

        return persistentUser;
    }

    public Users addUser(Users users) {
        return repository.save(users);
    }

    public Users editUser(Users users) {
        return repository.findById(users.getId())
                .map(i -> {
                    i.setFirstName(users.getFirstName());
                    i.setLastName(users.getLastName());
                    i.setAge(users.getAge());
                    return repository.save(i);
                }).orElse(null);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public Users findUser(long id) {
        return repository.findById(id).orElse(null);
    }
}
