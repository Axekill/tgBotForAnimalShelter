package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.animals.Dog;
import pro.sky.telegrambot.service.DogService;

import java.util.Collection;

@RestController
public class DogController {

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @PostMapping("/addDog")
    public Dog addDog(@RequestBody Dog dog){
        return service.addDog(dog);
    }

    @PutMapping("{id}/editDog")
    public Dog editDog(@RequestBody Dog dog, @PathVariable long id){
        return service.editDog(dog);
    }
    @DeleteMapping("{id}/deleteDog")
    public void deleteDog(@PathVariable long id){
        service.deleteDog(id);
    }

    @GetMapping("{id}/findDog")
    public Dog findCat(@PathVariable long id){
        return service.findDog(id);
    }

    @GetMapping("/allDogs")
    public Collection<Dog> allDogs(){
        return service.getAll();
    }

}
