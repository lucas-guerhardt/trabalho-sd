package com.lp.users.services;

import java.util.List;

import com.lp.users.models.dto.UserCreate;
import com.lp.users.models.dto.UserGet;
import com.lp.users.models.dto.UserGetAll;
import com.lp.users.models.dto.UserUpdate;

public interface UserService {
    List<UserGet> get();
    UserGet get(Long id);
    UserGetAll get(String email);
    Long create(UserCreate user);
    Long update(Long id, UserUpdate user);
    void delete(Long id);
}