package ru.shytov;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shytov.dao.Dao;
import ru.shytov.model.Admin;
import ru.shytov.model.Manufacturer;
import ru.shytov.service.AdminDaoImpl;
import ru.shytov.service.ManufacturerDaoImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        ManufacturerDaoImpl manufacturerDao = new ManufacturerDaoImpl(factory);

        List<Manufacturer> manu = manufacturerDao.findAll();

        for(Manufacturer manufacturer : manu){
            System.out.println(manufacturer);
        }
    }
}
