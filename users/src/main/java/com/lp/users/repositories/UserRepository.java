package com.lp.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lp.users.models.UserModel;
import com.lp.users.models.enums.Occupation;
import com.lp.users.models.enums.Role;

import java.util.List;


public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
    UserModel findByCpf(String cpf);
    List<UserModel> findByRole(Role role);
    List<UserModel> findByOccupation(Occupation occupation);
    Boolean existsByEmail(String email);
}
