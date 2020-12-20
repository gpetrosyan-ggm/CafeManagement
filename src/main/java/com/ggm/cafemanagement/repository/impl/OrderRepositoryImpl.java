package com.ggm.cafemanagement.repository.impl;

import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.repository.OrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Order o set o.status=:status, o.comment=:comment where o.id = :id");
        query.setParameter("status", order.getStatus());
        query.setParameter("comment", order.getComment());
        query.setParameter("id", order.getId());
        query.executeUpdate();
    }

    @Override
    public Optional<Order> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("from Order u where u.id = :id", Order.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("from Order ", Order.class);
        return query.list();
    }

}
