package com.example.projectbe.core.service;

import com.example.projectbe.core.dto.UserBasicDto;
import com.example.projectbe.domain.entity.User;

public interface UserService {

    User findByEmail(String email);

    User findUserByEmail(String email);

    UserBasicDto getByEmail(String email);
}
