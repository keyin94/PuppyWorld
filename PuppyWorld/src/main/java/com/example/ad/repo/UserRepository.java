package com.example.ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ad.domain.User;


public interface UserRepository extends JpaRepository<User, Integer>{

}
