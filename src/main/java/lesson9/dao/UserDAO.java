package lesson9.dao;

import lesson9.entity.User;

import java.util.Optional;

public interface UserDAO {

    User save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByName(String name);
    Long deleteById(Long userId);

}
