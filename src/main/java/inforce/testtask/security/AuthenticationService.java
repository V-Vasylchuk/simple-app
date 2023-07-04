package inforce.testtask.security;

import inforce.testtask.dto.mapper.request.UserRequestDto;
import inforce.testtask.exception.AuthenticationException;
import inforce.testtask.model.User;

public interface AuthenticationService {
    User register(UserRequestDto userRequestDto);

    User login(String email, String password) throws AuthenticationException;

    String encodePassword(String password);
}
