package com.lp.auth.services;

import com.lp.auth.models.dto.LoginDto;

public interface AuthService {
    public String login(LoginDto loginDto);
    public String logout(String token);
    public String validateToken(String token);
}
