package com.youth.moim.domain;

import java.util.Optional;

public interface UserReader {
    Optional<User> findByEmail(String email);
}
