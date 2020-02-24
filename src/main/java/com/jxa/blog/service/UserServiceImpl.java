package com.jxa.blog.service;

import com.jxa.blog.po.User;
import com.jxa.blog.repository.UserRepository;
import com.jxa.blog.utils.SecurityMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*添加注解*/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getOneUser(String username, String password) {
        return userRepository.getByUsernameAndPassword(username,SecurityMD5.code(password));
    }
}
