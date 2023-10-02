package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.shelters.DogShelter;
import pro.sky.telegrambot.service.DogShService;

import java.util.Collection;

@RestController
public class DogShController {

    private final DogShService service;

    public DogShController(DogShService service) {
        this.service = service;
    }
    @PostMapping("/addDogShelter")
    public DogShelter addCat(@RequestBody DogShelter dogS){
        return service.addDogSh(dogS);
    }

    @PutMapping("{id}/editDogShelter")
    public DogShelter editDogShelter(@RequestBody DogShelter dogS, @PathVariable long id){
        return service.editDogShelter(dogS);
    }
    @DeleteMapping("{id}/deleteDogShelters")
    public void deleteDogShelter(@PathVariable long id){
        service.deleteDogShelter(id);
    }

    @GetMapping("{id}/findDogShelter")
    public DogShelter findDogShelter(@PathVariable long id){
        return service.findShelterForDogs(id);
    }

    @GetMapping("/allDogShelters")
    public Collection<DogShelter> allCats(){
        return service.getAll();
    }

}
