package pro.sky.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.repository.CatRepository;

import java.util.Collection;

@Service
public class CatService {
    private final CatRepository repository;

    @Autowired
    public CatService(CatRepository repository) {
        this.repository = repository;
    }
    public Cat addCat(Cat cat) {
        return repository.save(cat);
    }
    public Cat findCat(Long id) {
        return repository.getById(id);
    }

    public Cat editCat(Cat cat) {
        return repository.findById(cat.getId())
                .map(i -> {
                    i.setName(cat.getName());
                    i.setAge(cat.getAge());
                    return repository.save(i);
                }).orElse(null);
    }
    public void deleteCat(Long id) {
        repository.deleteById(id);
    }

    public Collection<Cat> getAll(){
        return repository.findAll();
    }











}
