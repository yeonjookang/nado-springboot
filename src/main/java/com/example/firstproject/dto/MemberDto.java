package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

public class MemberDto {
    private String email;
    private String password;

    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member toEntity(){
        return new Member(null,email,password);
    }
}
