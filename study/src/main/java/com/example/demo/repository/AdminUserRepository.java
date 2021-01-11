package com.example.demo.repository;

import com.example.demo.model.entity.AdminUser;
import com.example.demo.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
}
