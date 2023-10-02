package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.model.shelters.DogShelter;
import pro.sky.telegrambot.service.UsersService;

import java.util.Collection;

@RestController
public class UsersController {
    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }
    @GetMapping("/allUsers")
    public Collection<Users> allUsers(){
        return service.allUsers();
    }
}
