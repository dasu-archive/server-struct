package com.dasu.common.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

public class AppUserDto {

    @Getter
    public static class SignInRequest{
        private String email;
        private String password;
    }
}
