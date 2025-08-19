package com.jabes.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jabes.myproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  User findByUsername(String username);
}
