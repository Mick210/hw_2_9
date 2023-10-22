package com.example.hw_2_9.Service;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public Employee findMinSalaryByDepartment(int numberOfDepartment);

    public Employee findMaxSalaryByDepartment(int numberOfDepartment);

    public int getTotalSalaryCostByDepartment(int department);

    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(int department);

    public Map<Integer, List<Employee>> getAllEmployees();


}
