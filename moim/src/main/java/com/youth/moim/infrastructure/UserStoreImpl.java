package com.youth.moim.infrastructure;

import com.youth.moim.domain.User;
import com.youth.moim.domain.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {
    private final UserRepository userRepository;
    public void registerUser(User entity) {
        userRepository.save(entity);
    }
}
