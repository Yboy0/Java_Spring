package com.example.friends.repository;

import com.example.friends.domain.Person;
import com.example.friends.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonRepositoryTests {

    @Autowired //PersonRepository에 bean을 주입
    private PersonRepository personRepository;

    @Test
    public void crud(){
        Person person= Person.builder()
                .name("Yboy")
                .age(25)
                .bloodType("AB")
                .address("Suwon")
                .hobby("listening music")
                .birthday(new Birthday())
                .job("student")
                .build();

        personRepository.save(person);

        List<Person> people = personRepository.findAll();

        assertThat(people.get(0).getId()).isEqualTo(1L);

        System.out.println(people.get(0));
    }
    @Test
    void hashCodeAndEquals(){
        Person person1 = Person.builder()
                .name("martin")
                .age(10)
                .build();
        Person person2 = Person.builder()
                .name("martin")
                .age(10)
                .build();

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }
}