package com.youth.moim.api;


import com.youth.moim.presentation.auth.api.SignInApi;

public class Scenario {
    public static SignInApi signInOrganizerApi() {
        return new SignInApi();
    }
}
