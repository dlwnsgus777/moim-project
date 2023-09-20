package com.youth.moim.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginUser {
    private final Long idx;
    private final String id;

    @Builder
    public LoginUser(Long idx, String id) {
        this.idx = idx;
        this.id = id;
    }
}
