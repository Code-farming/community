package com.lhb.community.service;

import com.lhb.community.mapper.UserMapper;
import com.lhb.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        User user1 = userMapper.findByAccountId(user.getAccountId());
        if (user1 == null){
            user1.setGmtCreate(System.currentTimeMillis());
            user1.setGmtModified(user1.getGmtCreate());
            userMapper.insert(user1);
        }else {
            user1.setGmtModified(System.currentTimeMillis());
            user1.setAvatarUrl(user.getAvatarUrl());
            user1.setBio(user.getBio());
            user1.setToken(user.getToken());
            user1.setName(user.getName());
            userMapper.update(user1);
        }

    }
}
