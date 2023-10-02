package pro.sky.telegrambot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.model.shelters.DogShelter;
import pro.sky.telegrambot.service.VolunteerService;

import java.util.Collection;

@RestController
public class VolunteerController {

    private final VolunteerService service;

    public VolunteerController(VolunteerService service) {
        this.service = service;
    }
    @GetMapping("/allVolunteers")
    public Collection<Volunteer> allVolunteers(){
        return service.allVolunteer();
    }
}
