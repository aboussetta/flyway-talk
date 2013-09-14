package com.github.skosmalla.flyway.demo.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.github.skosmalla.flyway.demo.webapp.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class PersonRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired(required = true)
    public PersonRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public PersonRepository() {

    }


    public List<Person> findAllPersons() {
        return jdbcTemplate.query("Select * from person;", new RowMapper<Person>() {

            @Override
            public Person mapRow(ResultSet resultSet, int arg1) throws SQLException {
                Person person = new Person();
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                return person;
            }

        });

    }


    public void save(Person person) {
        jdbcTemplate.update("Insert into person (first_name,last_name) values(?,?)", person.getFirstName(),
                person.getLastName());
    }
}
