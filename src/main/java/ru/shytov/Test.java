package ru.shytov;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.dao.Dao;
import ru.shytov.model.Admin;
import ru.shytov.service.AdminDaoImpl;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        AdminDaoImpl adminDao = new AdminDaoImpl(factory);

        System.out.println(adminDao.searchByLogin("1"));
    }
}
