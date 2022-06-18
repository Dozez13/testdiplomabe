package com.example.projectbe.core.mapper;

import com.example.projectbe.core.dto.UserBasicDto;
import com.example.projectbe.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserBasicDto toUserBasicDto(User user){
        UserBasicDto userBasicDto = new UserBasicDto();
        userBasicDto.setFirstName(user.getFirstName());
        userBasicDto.setLastName(user.getLastName());
        return userBasicDto;
    }
}
