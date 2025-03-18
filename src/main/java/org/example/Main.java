package org.example;

import net.datafaker.Faker;
import org.example.persistence.ContactDAO;
import org.example.persistence.EmployeeDAO;
import org.example.persistence.EmployeeParamDAO;
import org.example.persistence.entity.ContactEntity;
import org.example.persistence.entity.EmployeeAuditDAO;
import org.example.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private final static ContactDAO contactDAO = new ContactDAO();
    private final static Faker faker = new Faker(Locale.of("pt", "BR"));

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/jdbc-sample", "root", "root")
                .load();
        flyway.migrate();

//        var insert = new EmployeeEntity();
//        insert.setName("Junior'");
//        insert.setSalary(new BigDecimal("4500"));
//        insert.setBirthday(OffsetDateTime.now().minusYears(18));
//        System.out.println(insert);
//        employeeDAO.insert(insert);
//        System.out.println(insert);
//
//        employeeDAO.findAll().forEach(System.out::println);
//
//        System.out.println(employeeDAO.findById(1));
//
//        var update = new EmployeeEntity();
//        update.setId(update.getId());
//        update.setName("Carlos");
//        update.setSalary(new BigDecimal("4500"));
//        update.setBirthday(OffsetDateTime.now().minusYears(26).minusDays(1));
//        employeeDAO.update(update);
//
//        employeeDAO.delete(insert.getId());
//
//        employeeAuditDAO.findAll().forEach(System.out::println);

//        var entities = Stream.generate(() ->{
//            var employee = new EmployeeEntity();
//            employee.setName(faker.name().fullName());
//            employee.setSalary(new BigDecimal(faker.number().digits(4)));
//            employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40,20)), LocalTime.MIN, UTC));
//            return employee;
//        }).limit(10000).toList();
//
//        employeeDAO.insertBatch(entities);

//        var employee = new EmployeeEntity();
//        employee.setName("carlos");
//        employee.setSalary(new BigDecimal("4500"));
//        employee.setBirthday(OffsetDateTime.now().minusYears(18));
//        System.out.println(employee);
//        employeeDAO.insert(employee);
//        System.out.println(employee);
//
//        var contact = new ContactEntity();
//        contact.setDescription("carlos@carlos.com");
//        contact.setType("e-mail");
//        contact.setEmployee(employee);
//        contactDAO.insert(contact);

        employeeDAO.findAll().forEach(System.out::println);
    }
}