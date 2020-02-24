package com.jxa.blog.service;

import com.jxa.blog.po.User;

public interface UserService {
    User getOneUser(String username,String password);
}
