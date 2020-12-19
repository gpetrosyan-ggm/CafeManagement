package com.ggm.cafemanagement.repository.impl;

import com.ggm.cafemanagement.domain.entity.User;
import com.ggm.cafemanagement.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public List<User> findAllWaiters() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User u where u.role = 'WAITER'", User.class).list();
    }

    @Override
    public Optional<User> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User u where u.userName = :userName", User.class);
        query.setParameter("userName", userName);
        return query.uniqueResultOptional();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

}
