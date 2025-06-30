package com.jobtrack.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email 已被註冊");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username 已被使用");
        }

        User user = User.builder()
                .username(request.getUsername())    // 一定要帶 username
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .build();

        userRepository.save(user);

        return new RegisterResponse("註冊成功！");
    }
}
