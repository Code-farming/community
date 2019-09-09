package com.lhb.community.mapper;

import com.lhb.community.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    void insert(User user);

    User findByToken(@Param("token") String token);

    User findById(@Param("accountId") Integer accountId);

}
