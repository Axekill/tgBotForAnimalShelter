package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.animals.Dog;
import pro.sky.telegrambot.model.shelters.ShelterForCats;
import pro.sky.telegrambot.repository.DogRepository;

import java.util.Collection;

@Service
public class DogService {

    private final DogRepository repository;

    @Autowired
    public DogService(DogRepository repository) {
        this.repository = repository;
    }
    public Dog addDog(Dog dog){
        return repository.save(dog);
    }

    public Dog findDog(Long id){
        return repository.getById(id);
    }

    public Dog editDog(Dog dog) {
        return repository.findById(dog.getId())
                .map(i -> {
                    i.setName(i.getName());
                    i.setAge(i.getAge());
                    return repository.save(i);
                }).orElse(null);
    }

    public void deleteDog(Long id) {
        repository.deleteById(id);
    }

    public Collection<Dog> getAll(){
        return repository.findAll();
    }

}
