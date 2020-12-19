package com.ggm.cafemanagement.repository.impl;

import com.ggm.cafemanagement.domain.entity.Product;
import com.ggm.cafemanagement.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product", Product.class).list();
    }

    @Override
    public Optional<Product> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("from Product p where p.id = :id", Product.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

}
