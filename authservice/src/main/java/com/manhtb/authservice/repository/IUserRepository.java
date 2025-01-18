package com.manhtb.authservice.repository;

import com.manhtb.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    public User findUserByUsername(String username);
}
