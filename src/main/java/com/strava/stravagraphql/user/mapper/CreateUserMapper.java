package com.strava.stravagraphql.user.mapper;

import com.strava.stravagraphql.user.dto.CreateUserDto;
import com.strava.stravagraphql.user.dto.RegisterResponse;
import com.strava.stravagraphql.user.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateUserMapper {
    @Mapping(target = "id", ignore = true)
    User createUserDTOToUser(CreateUserDto createUserDto);

    RegisterResponse userToRegisterResponse(User user);

}
