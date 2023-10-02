package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.Collection;

@Service
public class VolunteerService {

    private final VolunteerRepository repository;
    @Autowired
    public VolunteerService(VolunteerRepository repository) {
        this.repository = repository;
    }

    public Collection<Volunteer> allVolunteer(){
        return repository.findAll();
    }
}
