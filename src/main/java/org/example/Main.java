package org.example;

import org.example.persistence.EmployeeDAO;
import org.example.persistence.entity.EmployeeAuditDAO;
import org.example.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/jdbc-sample", "root", "root")
                .load();
        flyway.migrate();

        var insert = new EmployeeEntity();
        insert.setName("Junior");
        insert.setSalary(new BigDecimal("4500"));
        insert.setBirthday(OffsetDateTime.now().minusYears(18));
        System.out.println(insert);
        employeeDAO.insert(insert);
        System.out.println(insert);
//
//        employeeDAO.findAll().forEach(System.out::println);
//
//        System.out.println(employeeDAO.findById(1));
//
        var update = new EmployeeEntity();
        update.setId(update.getId());
        update.setName("Carlos");
        update.setSalary(new BigDecimal("4500"));
        update.setBirthday(OffsetDateTime.now().minusYears(26).minusDays(1));
        employeeDAO.update(update);

        employeeDAO.delete(insert.getId());

        employeeAuditDAO.findAll().forEach(System.out::println);
    }
}