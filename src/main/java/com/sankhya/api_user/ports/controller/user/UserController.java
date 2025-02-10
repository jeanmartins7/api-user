package com.sankhya.api_user.ports.controller.user;

import com.sankhya.api_user.application.dto.UserRequestDTO;
import com.sankhya.api_user.application.dto.UserResponseDTO;
import com.sankhya.api_user.application.usecase.users.CreateUserUseCase;
import com.sankhya.api_user.application.usecase.users.FindUserByEmailUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private FindUserByEmailUseCase findUserByEmailUseCase;

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody final UserRequestDTO request) {
        return createUserUseCase.execute(request);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestHeader("X-User-Email") String email) {
        final UserResponseDTO user = findUserByEmailUseCase.execute(email);
        return ResponseEntity.ok(user);
    }
}