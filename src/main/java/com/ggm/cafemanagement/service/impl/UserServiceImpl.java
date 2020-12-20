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

/**
 * Save class to save/get user data.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * Finding all users.
     *
     * @return list of {@link UserDto} fetched from DB.
     */
    @Override
    @Transactional
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return mapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    /**
     * Finding all users with 'WAITER' role.
     *
     * @return list of {@link UserDto} fetched from DB.
     */
    @Override
    @Transactional
    public List<UserDto> findAllWaiters() {
        List<User> users = userRepository.findAllWaiters();
        return mapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    /**
     * Saving user data into the DB.
     *
     * @param userDto new {@link UserDto} going to be saved.
     */
    @Override
    @Transactional
    public void save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    /**
     * Find logins user data.
     *
     * @return {@link UserDto} logins user.
     */
    @Override
    @Transactional
    public UserDto findAuthUser() {
        String userName = SecurityHelper.retrieveUserName();
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Could not found user by user name %s", userName)));
        return mapper.map(user, UserDto.class);
    }

}
