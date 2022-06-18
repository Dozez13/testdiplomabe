package com.example.projectbe.core.service.impl;

import com.example.projectbe.core.dto.UserBasicDto;
import com.example.projectbe.core.expection.UserNotFoundExceptionRuntimeException;
import com.example.projectbe.core.mapper.UserMapper;
import com.example.projectbe.core.service.UserService;
import com.example.projectbe.domain.entity.User;
import com.example.projectbe.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundExceptionRuntimeException("User not found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserBasicDto getByEmail(String email) {
        return userMapper.toUserBasicDto(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundExceptionRuntimeException("User not found")));
    }
}
