package com.neos.tusa;

import com.neos.tusa.dto.UserCreateRequest;
import com.neos.tusa.dto.UserResponse;
import com.neos.tusa.model.User;
import com.neos.tusa.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class Controller {

    private final UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setTelegramId("telegram");
        userRepository.save(user);
        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getTelegramId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
