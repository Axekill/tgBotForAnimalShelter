package pro.sky.telegrambot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.shelters.ShelterForCats;
import pro.sky.telegrambot.service.CatShService;

import java.util.Collection;
@RestController
public class CatShController {





    private final CatShService service;
    public CatShController(CatShService service) {
        this.service = service;
    }

    @PostMapping("/addCatShelter")
    public ShelterForCats addCatShelter(@RequestBody ShelterForCats catSh){
        return service.addCatSh(catSh);
    }

    @PutMapping("{id}/editCatShelter")
    public ShelterForCats editCatShelter(@RequestBody ShelterForCats catSh, @PathVariable long id){
        return service.editCatShelter(catSh);
    }
    @DeleteMapping("{id}/deleteCatShelter")
    public void deleteCatShelter(@PathVariable long id){service.deleteCatShelter(id);
    }

    @GetMapping("{id}/findCatShelter")
    public ShelterForCats findCatShelter(@PathVariable long id){
        return service.findShelterForCats(id);
    }

    @GetMapping("/allCatShelders")
    public Collection<ShelterForCats> allCats(){
        return service.getAll();
    }


}
