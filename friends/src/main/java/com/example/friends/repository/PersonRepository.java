package com.example.friends.repository;

import com.example.friends.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person save(Person person);

    List<Person> findAll();
}
