package com.example.hw_2_9.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public abstract class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public DepartmentServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @Override
    public Employee findMinSalaryByDepartment(int numberOfDepartment) {
        return employeeServiceImpl.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == numberOfDepartment)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Override
    public Employee findMaxSalaryByDepartment(int numberOfDepartment) {
        return employeeServiceImpl.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == numberOfDepartment)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Override
    public int getTotalSalaryCostByDepartment(int department) {
        return employeeServiceImpl.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(int department) {
        return employeeServiceImpl.getEmployeeMap().stream()
                .filter(a -> a.getDepartment() == department)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeServiceImpl.getEmployeeMap().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
