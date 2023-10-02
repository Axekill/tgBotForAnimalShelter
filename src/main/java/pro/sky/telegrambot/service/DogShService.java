package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.shelters.DogShelter;
import pro.sky.telegrambot.model.shelters.ShelterForCats;
import pro.sky.telegrambot.repository.DogShRepository;

import java.util.Collection;
@Service
public class DogShService {

    private  final DogShRepository repository;

    public DogShService(DogShRepository repository) {
        this.repository = repository;
    }

    public DogShelter addDogSh(DogShelter shelterDogs){
        return repository.save(shelterDogs);
    }

    public DogShelter findShelterForDogs(Long id){
        return repository.getById(id);
    }

    public DogShelter editDogShelter(DogShelter dogShelter) {
        return repository.findById(dogShelter.getId())
                .map(i -> {
                    i.setDogs(i.getDogs());
                    i.setAddress(i.getAddress());
                    i.setNameShelter(i.getNameShelter());
                    i.setWorkingHours(i.getWorkingHours());
                    return repository.save(i);
                }).orElse(null);
    }

    public void deleteDogShelter(Long id) {
        repository.deleteById(id);
    }

    public Collection<DogShelter> getAll(){
        return repository.findAll();
    }
}
