package org.example.persistence;

import com.mysql.cj.jdbc.StatementImpl;
import org.example.persistence.entity.EmployeeEntity;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {

    public void insert(final EmployeeEntity entity){

        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "INSERT INTO employees (name, salary, birthday) values ('" +
                    entity.getName() + "', " +
                    entity.getSalary().toString() + ", " +
                    "'" + formatOffsetDateTime(entity.getBirthday()) +"')";
            statement.executeUpdate(sql);
            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
            if (statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());

        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    public void update(final EmployeeEntity entity){

    }

    public void delete(final EmployeeEntity entity){

    }

    public List<EmployeeEntity> findAll(){
        List<EmployeeEntity> entities = new ArrayList<>();
        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            statement.executeQuery("SELECT * FROM employees ORDER by name");
            var resultSet = statement.getResultSet();
            while (resultSet.next()){
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                entities.add(entity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();
        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    private String formatOffsetDateTime(final OffsetDateTime dateTime){
        var utcDateTime = dateTime.withOffsetSameInstant(UTC);
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
