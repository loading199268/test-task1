package com.example.testtask1.repo;

import com.example.testtask1.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);

    boolean existsByUserName(String userName);

}
