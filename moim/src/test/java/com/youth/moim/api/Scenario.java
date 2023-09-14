package com.youth.moim.api;

import com.youth.moim.presentation.user.api.SignInOrganizerApi;

public class Scenario {
    public static SignInOrganizerApi signInOrganizerApi() {
        return new SignInOrganizerApi();
    }
}
