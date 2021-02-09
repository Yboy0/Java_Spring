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
    void findByName(){
       List<Person> people = personRepository.findByName("paul");
       assertThat(people.size()).isEqualTo(1);

       Person person = people.get(0);
       assertAll(
               () -> assertThat(person.getJob()).isEqualTo("officer"),
               () -> assertThat(person.getHobby()).isEqualTo("reading")
       );

   }

   @Test
    void findByNameIfDeleted(){
       List<Person> people = personRepository.findByName("andrew");

       assertThat(people.size()).isEqualTo(0);
   }

   @Test
    void findByMonthOfBirthday(){
       List<Person> people = personRepository.findByMonthOfBirthday(7);

       assertThat(people.size()).isEqualTo(2);
       assertAll(
               () -> assertThat(people.get(0).getName()).isEqualTo("martin")
       );
   }


}