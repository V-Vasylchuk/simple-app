package inforce.testtask.service;

import inforce.testtask.model.User;
import java.util.Optional;

public interface UserService {
    User save(User user);

    User update(User user);

    Optional<User> findByEmail(String email);
}
