package com.example.userbackenddemo.mapper;

import com.example.userbackenddemo.dto.UserDto;
import com.example.userbackenddemo.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setAvatar(user.getAvatar());
        return  userDto;
    }
}
