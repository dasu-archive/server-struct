package com.dasu.authentication.controller;


import com.dasu.common.domain.user.dto.AppUserDto;
import com.dasu.common.domain.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/sign-in")
    public String signIn(@RequestBody AppUserDto.SignInRequest request) {
        System.out.println("signRequest");
        return appUserService.signIn(request.getEmail(), request.getPassword());
    }

    @PostMapping("/sign-up")
    public String signup(@RequestBody AppUserDto.SignInRequest request) {
        System.out.println("signRequest");
        if(appUserService.signUp(request.getEmail(), request.getPassword())){
            return "ok";
        }else{
            return "fail";
        }
    }


    @GetMapping("/sign-in")
    public String signIn() {
        System.out.println("GetsignRequest");
        return "ok";
    }
//    @PostMapping("/refresh")
//    public String refresh(@RequestParam String token) {
//
//    }

}
