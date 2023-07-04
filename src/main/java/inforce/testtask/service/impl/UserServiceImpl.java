package inforce.testtask.service.impl;

import inforce.testtask.exception.DataProcessingException;
import inforce.testtask.model.User;
import inforce.testtask.repository.UserRepository;
import inforce.testtask.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        throw new DataProcessingException("Can't update, there is no user by id: " + user.getId());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
