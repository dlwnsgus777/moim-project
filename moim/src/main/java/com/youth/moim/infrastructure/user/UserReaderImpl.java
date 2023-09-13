package com.youth.moim.infrastructure.user;

import com.youth.moim.domain.user.User;
import com.youth.moim.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
