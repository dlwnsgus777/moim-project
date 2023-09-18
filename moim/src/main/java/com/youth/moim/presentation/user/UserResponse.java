package com.youth.moim.presentation.user;

import com.youth.moim.domain.user.UserInfo;

public class UserResponse {
    public record RetrieveUser(
            UserInfo.Main user
    ) {

    }
}
