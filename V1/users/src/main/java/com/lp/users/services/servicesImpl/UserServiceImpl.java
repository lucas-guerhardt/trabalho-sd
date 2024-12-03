package com.lp.users.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.users.models.UserModel;
import com.lp.users.models.dto.UserCreate;
import com.lp.users.models.dto.UserGet;
import com.lp.users.models.dto.UserGetAll;
import com.lp.users.models.dto.UserUpdate;
import com.lp.users.models.enums.Role;
import com.lp.users.repositories.UserRepository;
import com.lp.users.services.UserService;

@Service
public class UserServiceImpl implements UserService {    
    @Autowired
    private UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserGet> get() {
        List<UserModel> users = userRepository.findAll();
        List<UserGet> usersGet = new ArrayList<>();
        for (UserModel user : users) {
            usersGet.add(new UserGet(user.getName(), user.getEmail(), user.getCpf(), user.getOccupation(), user.getRole()));
        }
        return usersGet;
    }

    @Override
    public UserGet get(Long id) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        UserModel user = userRepository.findById(id).get();
        return new UserGet(user.getName(), user.getEmail(), user.getCpf(), user.getOccupation(), user.getRole());
    }

    @Override
    public UserGetAll get(String email) {
        UserModel user = userRepository.findByEmail(email);

        if(user == null) {
            return null;
        }

        return new UserGetAll(user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getPassword(), user.getOccupation(), user.getRole());
    }

    @Override
    public Long create(UserCreate userCreate) {
        if (userRepository.existsByEmail(userCreate.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        UserModel user = new UserModel(userCreate.getName(), userCreate.getEmail(), userCreate.getCpf(), passwordEncoder.encode(userCreate.getPassword()), userCreate.getOccupation(), Role.USER);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long update(Long id, UserUpdate userUpdate) {
        UserModel user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        if (userUpdate.getName() != null) {
            user.setName(userUpdate.getName());
        }
        if (userUpdate.getEmail() != null) {
            user.setEmail(userUpdate.getEmail());
        }
        if (userUpdate.getCpf() != null) {
            user.setCpf(userUpdate.getCpf());
        }
        if (userUpdate.getPassword() != null) {
            user.setPassword(userUpdate.getPassword());
        }
        if (userUpdate.getOccupation() != null) {
            user.setOccupation(userUpdate.getOccupation());
        }
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}