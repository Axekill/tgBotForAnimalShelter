package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.Users;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findUsersByTelegramUserId(Long id);

}
