package com.ggm.cafemanagement.repository.impl;

import com.ggm.cafemanagement.domain.entity.CafeTable;
import com.ggm.cafemanagement.repository.TableRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TableRepositoryImpl implements TableRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CafeTable> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from CafeTable", CafeTable.class).list();
    }

    @Override
    public Optional<CafeTable> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<CafeTable> query = session.createQuery("from CafeTable u where u.id = :id", CafeTable.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    @Override
    public void save(CafeTable cafeTable) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cafeTable);
    }

}
