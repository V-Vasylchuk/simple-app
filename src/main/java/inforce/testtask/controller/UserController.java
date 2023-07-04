package inforce.testtask.controller;

import inforce.testtask.dto.mapper.DtoMapper;
import inforce.testtask.dto.mapper.request.UserRequestDto;
import inforce.testtask.dto.mapper.response.UserResponseDto;
import inforce.testtask.exception.DataProcessingException;
import inforce.testtask.model.User;
import inforce.testtask.security.AuthenticationService;
import inforce.testtask.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final DtoMapper<UserRequestDto, UserResponseDto, User> userDtoMapper;
    private final AuthenticationService authenticationService;

    @GetMapping("/me")
    public UserResponseDto get(Authentication authentication) {
        return userDtoMapper.mapToDto(userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new DataProcessingException("Can't get information by user")));
    }

    @PutMapping("/me")
    public UserResponseDto update(Authentication authentication,
                                  @RequestBody @Valid UserRequestDto userRequestDto) {
        Long userId = userService.findByEmail(authentication.getName()).orElseThrow().getId();
        User user = userDtoMapper.mapToModel(userRequestDto);
        user.setId(userId);
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(authenticationService.encodePassword(user.getPassword()));
        return userDtoMapper.mapToDto(userService.update(user));
    }
}
