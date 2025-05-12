package com.dasu.common.domain.user.service;

import com.dasu.common.config.jwt.JwtProvider;
import com.dasu.common.domain.user.entity.AppUser;
import com.dasu.common.domain.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final JwtProvider jwtProvider;

    public String signIn(String email, String password) {

        AppUser appUser = appUserRepository.findByEmailAndPassword(email, password).orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", appUser.getRoles());
        return jwtProvider.generateToken(appUser.getEmail(), claims);
    }

}
