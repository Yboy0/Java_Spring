package com.example.friends.repository;

import com.example.friends.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person save(Person person);

    List<Person> findAll();

    List<Person> findByName(String name);

    List<Person> findByBlockIsNull(); //block이 차단이 되지 않은 경우

    List<Person> findByBloodType(String bloodType);

    // 이번 달 생일인 사람 목록
    List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);
}
