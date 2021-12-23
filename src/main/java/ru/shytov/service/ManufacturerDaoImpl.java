package ru.shytov.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.shytov.dao.Dao;
import ru.shytov.model.Manufacturer;

import java.util.List;

public class ManufacturerDaoImpl implements Dao<Manufacturer,Integer> {

    private final SessionFactory factory;

    public ManufacturerDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Manufacturer findById(Integer id) {
        try(Session session = factory.openSession()) {
            return session.get(Manufacturer.class,id);
        }
    }

    @Override
    public List<Manufacturer> findAll() {
        try(Session session = factory.openSession()) {
            return session.createQuery("FROM Manufacturer",Manufacturer.class).list();
        }
    }

    @Override
    public void save(Manufacturer manufacturer) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(manufacturer);
            transaction.commit();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.update(manufacturer);
            transaction.commit();
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        try(Session session = factory.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.delete(manufacturer);
            transaction.commit();
        }
    }
}
