package com.youth.moim.api;


import com.youth.moim.presentation.auth.api.SignInApi;
import com.youth.moim.presentation.auth.api.SignUpApi;

public class Scenario {
    public static SignInApi signInApi() {
        return new SignInApi();
    }

    public static SignUpApi signUpApi() {
        return new SignUpApi();
    }
}
