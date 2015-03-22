package com.spike.java;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Sajith on 3/22/2015.
 */

@XmlRootElement
public class TestBean {

    private Long employeeId;
    private String employeeName;
    private Double salary;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
