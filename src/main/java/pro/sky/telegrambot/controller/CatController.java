package pro.sky.telegrambot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.service.CatService;

import java.util.Collection;

@RestController
public class CatController {


    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }
    @PostMapping("/addCat")
    public Cat addCat(@RequestBody Cat cat){
        return catService.addCat(cat);
    }

    @PutMapping("{id}/editCat")
    public Cat editCat(@RequestBody Cat cat, @PathVariable long id){
        return catService.editCat(cat);
    }
    @DeleteMapping("{id}/deleteCat")
    public void deleteCat(@PathVariable long id){
         catService.deleteCat(id);
    }

    @GetMapping("{id}/findCat")
    public Cat findCat(@PathVariable long id){
        return catService.findCat(id);
    }

    @GetMapping("/allCats")
    public  Collection<Cat> allCats(){
        return catService.getAll();
    }


}
