package com.example.hw_2_9.Service;

import java.util.Collection;

public interface EmployeeService {
    public Employee addEmployee(String firstName, String lastName, int salary, int department);

    public Employee removeEmployee(String firstName, String lastName);

    public Employee findEmployee(String firstName, String lastName);

    public Collection getEmployeeMap();
}
