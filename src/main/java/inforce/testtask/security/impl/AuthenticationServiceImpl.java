package inforce.testtask.security.impl;

import inforce.testtask.dto.mapper.UserMapper;
import inforce.testtask.dto.mapper.request.UserRequestDto;
import inforce.testtask.exception.AuthenticationException;
import inforce.testtask.model.User;
import inforce.testtask.security.AuthenticationService;
import inforce.testtask.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRequestDto userRequestDto) {
        User user = userMapper.mapToModel(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user = userService.save(user);
        return user;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        throw new AuthenticationException("Incorrect username or password!");
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
