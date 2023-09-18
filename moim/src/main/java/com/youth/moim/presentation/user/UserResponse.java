package com.youth.moim.presentation.user;

import com.youth.moim.domain.user.UserInfo;
import lombok.Builder;

public class UserResponse {
    public record RetrieveUser(
            UserInfo.Main user
    ) {
        @Builder
        public RetrieveUser {
        }
    }
}
