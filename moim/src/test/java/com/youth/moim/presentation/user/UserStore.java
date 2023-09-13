package com.youth.moim.presentation.user;

public class UserStore {
    private final UserRepository userRepository;

    public UserStore(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User entity) {
        userRepository.save(entity);
    }
}
