package com.trungnguyen.springsecurityinaction.dto;

import com.trungnguyen.springsecurityinaction.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //@Mapping
    User dtoToUser(UserDto userDto);
}
