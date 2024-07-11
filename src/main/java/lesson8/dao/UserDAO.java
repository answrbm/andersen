package lesson8.dao;

import lesson8.model.UserImpl;

import java.util.Optional;

public interface UserDAO {

    UserImpl save(UserImpl user);
    Optional<UserImpl> findById(Long userId);
    Long deleteById(Long userId);

}
