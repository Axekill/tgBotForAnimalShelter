package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.shelters.ShelterForCats;

public interface CatShRepository extends JpaRepository<ShelterForCats, Long> {
}
