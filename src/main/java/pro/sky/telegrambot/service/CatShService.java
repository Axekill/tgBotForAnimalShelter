package pro.sky.telegrambot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.shelters.ShelterForCats;
import pro.sky.telegrambot.repository.CatShRepository;

import java.util.Collection;

@Service
public class CatShService {


    private final CatShRepository repository;

    @Autowired
    public CatShService(CatShRepository repository) {
        this.repository = repository;
    }

    public ShelterForCats addCatSh(ShelterForCats shelterCats){
        return repository.save(shelterCats);
    }

    public ShelterForCats findShelterForCats(Long id){
        return repository.getById(id);
    }

    public ShelterForCats editCatShelter(ShelterForCats shelterForCats) {
        return repository.findById(shelterForCats.getId())
                .map(i -> {
                    i.setCats(i.getCats());
                    i.setAddress(i.getAddress());
                    i.setNameShelter(i.getNameShelter());
                    i.setWorkingHours(i.getWorkingHours());
                    return repository.save(i);
                }).orElse(null);
    }

    public void deleteCatShelter(Long id) {
        repository.deleteById(id);
    }

    public Collection<ShelterForCats> getAll(){
        return repository.findAll();
    }


}
