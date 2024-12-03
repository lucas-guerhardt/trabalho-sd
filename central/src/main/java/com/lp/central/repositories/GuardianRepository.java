package com.lp.central.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lp.central.models.GuardianModel;

public interface GuardianRepository extends JpaRepository<GuardianModel, Long> {
    List<GuardianModel> findByName(String name);
    GuardianModel findByEmail(String email);
    GuardianModel findByCpf(String cpf);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);
}