package com.example.hw_2_9.Service;

import com.example.hw_2_9.exeption.EmployeeAlreadyAddedException;
import com.example.hw_2_9.exeption.EmployeeNotFoundException;
import com.example.hw_2_9.exeption.MaximumEmployeesException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employeeMap;
    private static final int MAX_EMPLOYEES = 10;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Collection<Employee> getEmployeeMap() {
        return this.employeeMap.values();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName , lastName, salary, department);

        if (!employeeMap.containsKey(employee.getFirstName() + employee.getLastName())) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
            return employee;
        } else if (employeeMap.size() > MAX_EMPLOYEES) {
            throw new MaximumEmployeesException("Максимальное количество сотрудников");
        }
        throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employeeMap.containsKey(firstName + lastName)) {
            employeeMap.remove(firstName + lastName);
            return new Employee(firstName, lastName);
        }
        throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (!employeeMap.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Такой сотрудник не найден");
        }
        return new Employee(firstName, lastName);
    }
}