package com.ggm.cafemanagement.repository.impl;

import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.domain.entity.ProductInOrder;
import com.ggm.cafemanagement.repository.ProductInOrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductInOrderRepositoryImpl implements ProductInOrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ProductInOrder productInOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productInOrder);
    }

    @Override
    public void update(ProductInOrder productInOrder) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update ProductInOrder u set u.amount=:amount, u.status=:status, u.comment=:comment where u.id = :id");
        query.setParameter("amount", productInOrder.getAmount());
        query.setParameter("status", productInOrder.getStatus());
        query.setParameter("comment", productInOrder.getComment());
        query.setParameter("id", productInOrder.getId());
        query.executeUpdate();
    }

    @Override
    public Optional<ProductInOrder> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<ProductInOrder> query = session.createQuery("from ProductInOrder u where u.id = :id", ProductInOrder.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

}
