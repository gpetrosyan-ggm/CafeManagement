package com.ggm.cafemanagement.service;

import com.ggm.cafemanagement.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    List<UserDto> findAllWaiters();

    void save(UserDto userDto);

    UserDto findAuthUser();

}
