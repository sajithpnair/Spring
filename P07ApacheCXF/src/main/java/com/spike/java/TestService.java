package com.spike.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Sajith on 3/22/2015.
 */

@Path("/test")
public class TestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TestBean testHello() {
        TestBean testBean = new TestBean();
        testBean.setEmployeeId(1001L);
        testBean.setEmployeeName("Test Employee");
        testBean.setSalary(9999999.9);
        return testBean;
    }
}
