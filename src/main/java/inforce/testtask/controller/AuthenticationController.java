package inforce.testtask.controller;

import inforce.testtask.dto.mapper.DtoMapper;
import inforce.testtask.dto.mapper.request.LoginRequestDto;
import inforce.testtask.dto.mapper.request.UserRequestDto;
import inforce.testtask.dto.mapper.response.UserResponseDto;
import inforce.testtask.exception.AuthenticationException;
import inforce.testtask.model.User;
import inforce.testtask.security.AuthenticationService;
import inforce.testtask.security.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    private final DtoMapper<UserRequestDto, UserResponseDto, User> userDtoMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        return userDtoMapper.mapToDto(authService.register(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        User user;
        try {
            user = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            String token = jwtTokenProvider.createToken(user.getEmail());
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now().toString());
            body.put("token", token);
            body.put("status", HttpStatus.OK.value());
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password!", e);
        }
    }
}
