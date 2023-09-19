package com.youth.moim.domain.user;

import java.util.Optional;

public interface UserReader {
    Optional<User> findByEmail(String email);

    User getByLoginId(String loginId);

    User getByIdx(Long idx);
}
