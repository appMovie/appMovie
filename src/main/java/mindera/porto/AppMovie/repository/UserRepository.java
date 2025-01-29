package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
