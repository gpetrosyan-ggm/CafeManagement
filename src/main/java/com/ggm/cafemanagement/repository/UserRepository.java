package com.ggm.cafemanagement.repository;

import com.ggm.cafemanagement.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    List<User> findAllWaiters();

    Optional<User> findById(Long id);

    Optional<User> findByUserName(String userName);

    void save(User user);

}
