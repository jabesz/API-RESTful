package com.jabes.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jabes.myproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
