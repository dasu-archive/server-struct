package com.dasu.common.domain.user.service;

import com.dasu.common.config.jwt.JwtProvider;
import com.dasu.common.domain.user.entity.AppUser;
import com.dasu.common.domain.user.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public String signIn(String email, String password) {

        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow();
        if(passwordEncoder.matches(password, appUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", appUser.getRoles());
            return jwtProvider.generateToken(appUser.getEmail(), claims);
        } else{
            throw new RuntimeException("no id");
        }

    }


    @Transactional
    public boolean signUp(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        appUserRepository.findByEmailAndPassword(email, encodedPassword).ifPresentOrElse(
                value -> {throw new RuntimeException("exist value");},
                ()->{ appUserRepository.save(AppUser.builder().name("test").email(email).password(encodedPassword).roles("test").build());
        });

        return true;
    }

}
