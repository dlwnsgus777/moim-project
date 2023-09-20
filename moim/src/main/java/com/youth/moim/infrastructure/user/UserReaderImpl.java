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

    @Override
    public User getByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    @Override
    public User getByIdx(Long idx) {
        return userRepository.findByIdx(idx)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }
}
