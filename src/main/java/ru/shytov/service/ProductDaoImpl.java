package ru.shytov.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.shytov.dao.Dao;
import ru.shytov.model.Manufacturer;
import ru.shytov.model.Product;

import java.util.List;

public class ProductDaoImpl implements Dao<Product,Integer> {
    private final SessionFactory factory;

    public ProductDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Product findById(Integer id) {
        try(Session session = factory.openSession()) {
            return session.get(Product.class,id);
        }
    }

    @Override
    public List<Product> findAll() {
        try(Session session = factory.openSession()) {
            return session.createQuery("FROM Product ",Product.class).list();
        }
    }

    @Override
    public void save(Product product) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    @Override
    public void update(Product product) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }
    }

    @Override
    public void delete(Product product) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
    }
}
