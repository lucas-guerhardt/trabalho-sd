package com.lp.users.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.users.models.UserModel;
import com.lp.users.models.dto.UserCreate;
import com.lp.users.models.dto.UserGet;
import com.lp.users.models.dto.UserUpdate;
import com.lp.users.models.enums.Role;
import com.lp.users.repositories.UserRepository;
import com.lp.users.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        UserModel user = userRepository.findById(id).get();
        return new UserGet(user.getName(), user.getEmail(), user.getCpf(), user.getOccupation(), user.getRole());
    }

    @Override
    public Long create(UserCreate userCreate) {
        UserModel user = new UserModel(userCreate.getName(), userCreate.getEmail(), userCreate.getCpf(), userCreate.getPassword(), userCreate.getOccupation(), Role.USER);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long update(Long id, UserUpdate userUpdate) {
        UserModel user = userRepository.findById(id).get();
        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());
        user.setCpf(userUpdate.getCpf());
        user.setPassword(userUpdate.getPassword());
        user.setOccupation(userUpdate.getOccupation());
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
}