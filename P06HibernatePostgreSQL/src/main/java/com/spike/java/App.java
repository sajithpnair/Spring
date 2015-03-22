package com.spike.java;

import com.spike.java.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        Session session = factory.openSession();
        session.beginTransaction();
        Employee employee = new Employee();
        employee.setEmployeeId(1001l);
        employee.setEmployeeName("User One");
        employee.setSalary(2000000.00);
        session.save(employee);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }
}
