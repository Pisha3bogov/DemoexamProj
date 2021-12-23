package ru.shytov.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.shytov.dao.Dao;
import ru.shytov.model.ProductSales;

import java.util.List;

public class ProductSalesDaoImpl implements Dao<ProductSales,Integer> {
    private final SessionFactory factory;

    public ProductSalesDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public ProductSales findById(Integer id) {
        try(Session session = factory.openSession()) {
            return session.get(ProductSales.class,id);
        }
    }

    @Override
    public List<ProductSales> findAll() {
        try(Session session = factory.openSession()) {
            return session.createQuery("FROM ProductSales ",ProductSales.class).list();
        }
    }

    @Override
    public void save(ProductSales productSales) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(productSales);
            transaction.commit();
        }
    }

    @Override
    public void update(ProductSales productSales) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(productSales);
            transaction.commit();
        }
    }

    @Override
    public void delete(ProductSales productSales) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(productSales);
            transaction.commit();
        }
    }
}
