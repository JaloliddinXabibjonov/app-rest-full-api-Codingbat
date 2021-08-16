package mypackage.apprestfullapicodingbat.repository;

import mypackage.apprestfullapicodingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Integer id);
}
