package com.youth.moim.presentation.user;

import groovy.transform.builder.Builder;

public class User {
    private Long idx;
    private String name;
    private String birth;
    private Gender gender;
    private String id;
    private String password;
    private String email;
    private String company;

    @Builder
    public User(Long idx, String name, String birth, Gender gender, String id, String password, String email, String company) {
        this.idx = idx;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.id = id;
        this.password = password;
        this.email = email;
        this.company = company;
    }

    public Long getIdx() {
        return idx;
    }

    public void assignId(Long idx) {
        this.idx = idx;
    }

    public String getEmail() {
        return email;
    }
}
