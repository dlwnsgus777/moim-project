package com.youth.moim.presentation.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long nextId = 1L;

    public void save(final User user) {
        user.assignId(nextId);
        nextId++;
        users.put(user.getIdx(), user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }
}
