package com.dasu.authentication.controller;


import com.dasu.common.domain.user.dto.AppUserDto;
import com.dasu.common.domain.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/sign-in")
    public String signIn(@RequestBody AppUserDto.SignInRequest request) {
        return appUserService.signIn(request.getEmail(), request.getPassword());
    }

//    @PostMapping("/refresh")
//    public String refresh(@RequestParam String token) {
//
//    }

}
