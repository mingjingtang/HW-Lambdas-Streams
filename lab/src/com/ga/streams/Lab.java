package com.ga.streams;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
       list.forEach(element -> System.out.println(element));
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employees = this.employees.stream()
                .filter(employee -> employee.getSalary() >= 50_000)
                .collect(Collectors.toList());
        printList(employees);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<String> employeeHiredAfter2012 = this.employees.stream()
                .filter(employee -> !employee.getHireDate().isBefore(LocalDate.of(2012,1,1)))
                .map(employee -> employee.getName())
                .collect(Collectors.toList());
        printList(employeeHiredAfter2012);
    }

    @Test
    public void getMaxSalary() {
        double maxSalary = this.employees.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0);
        System.out.println("Max:" + maxSalary);

    }

    @Test
    public void getMinSalary() {
        double minSalary = this.employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
        System.out.println("Min:" + minSalary);
    }

    @Test
    public void getAverageSalaries() {
        double averageMale = this.employees.stream()
                .filter(employee -> employee.getGender().equals("Male"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(Double.NaN);
        double averageFemale = this.employees.stream()
                .filter(employee -> employee.getGender().equals("Female"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(Double.NaN);

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = this.employees.stream()
                .reduce((employee, employee2) -> employee.getSalary() > employee2.getSalary() ? employee : employee2)
                .orElse(null);
        System.out.println(highest);
    }
}

