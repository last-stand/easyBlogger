package com.easyblogger.repository;

import com.easyblogger.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer>{
    Optional<UserInfo> findOneByEmail(String email);
    Optional<UserInfo> findOneByUsername(String username);
}
