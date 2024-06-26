package com.example.lab8.repository;

import com.example.lab8.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
    public Users findByEmail(String email);
}