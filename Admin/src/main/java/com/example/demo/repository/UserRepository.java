package com.example.demo.repository;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
    //select * from user where account = ?
    Optional<User> findByAccount(String account);

    //select * from user where email = ?
    Optional<User> findByEmail(String Email);

    //select * from user where account and email = ?
    Optional<User> findByAccountAndEmail(String account,String Email);

}