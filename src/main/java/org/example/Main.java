package org.example;

import org.example.persistence.EmployeeDAO;
import org.example.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/jdbc-sample", "root", "root")
                .load();
        flyway.migrate();

//        var employee = new EmployeeEntity();
//        employee.setName("Junior");
//        employee.setSalary(new BigDecimal("4500"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(18));
//        System.out.println(employee);
//        employeeDAO.insert(employee);
//        System.out.println(employee);

//        employeeDAO.findAll().forEach(System.out::println);

//        System.out.println(employeeDAO.findById(1));

//        var employee = new EmployeeEntity();
//        employee.setId(2);
//        employee.setName("Marinho");
//        employee.setSalary(new BigDecimal("2500"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(26).minusDays(2));
//        employeeDAO.update(employee);

//        employeeDAO.delete(3L);
    }
}