package com.neos.tusa;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/create")
    public User createUser() {
        User user = new User();
        user.setName("matthew");
        user.setTelegramId("telegram");
        userRepository.save(user);
        return user;
    }
}
