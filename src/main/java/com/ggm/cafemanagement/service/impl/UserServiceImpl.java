package com.ggm.cafemanagement.service.impl;

import com.ggm.cafemanagement.domain.dto.UserDto;
import com.ggm.cafemanagement.domain.entity.User;
import com.ggm.cafemanagement.repository.UserRepository;
import com.ggm.cafemanagement.service.UserService;
import com.ggm.cafemanagement.util.SecurityHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return mapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public List<UserDto> findAllWaiters() {
        List<User> users = userRepository.findAllWaiters();
        return mapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public void save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDto findAuthUser() {
        String userName = SecurityHelper.retrieveUserName();
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Could not found user by user name %s", userName)));
        return mapper.map(user, UserDto.class);
    }

}
