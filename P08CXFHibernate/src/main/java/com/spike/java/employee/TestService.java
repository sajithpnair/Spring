package com.spike.java.employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Sajith on 3/22/2015.
 */

@Path("/test")
@Transactional
public class TestService {

    @Resource
    SessionFactory sessionFactory;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee testHello() {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.get(Employee.class, 1001);
        return employee;
    }
}
