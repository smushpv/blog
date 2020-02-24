package com.jxa.blog.repository;

import com.jxa.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsernameAndPassword(String username,String password);
}
