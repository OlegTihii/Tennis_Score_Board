package com.example.tennisscoreboard.repository;

import com.example.tennisscoreboard.util.HibernateSessionFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<E, K> implements CrudRepository<E, K> {

    private final Class<E> clazz;
    protected final SessionFactory sessionFactory = HibernateSessionFactory.getSession().getSessionFactory();

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT e FROM " + clazz.getName() + " e";
            return session.createQuery(jpql, clazz).getResultList();
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(clazz, id));
        }

    }

    @Override
    public E save(E entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;

        }
    }

    @Override
    public void update(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.merge(entity);
        }
    }

    @Override
    public void remove(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(session.get(clazz, entity));
        }
    }
}
