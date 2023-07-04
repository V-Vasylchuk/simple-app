package inforce.testtask.dto.mapper;

import inforce.testtask.dto.mapper.request.UserRequestDto;
import inforce.testtask.dto.mapper.response.UserResponseDto;
import inforce.testtask.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoMapper<UserRequestDto, UserResponseDto, User> {
    @Override
    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
