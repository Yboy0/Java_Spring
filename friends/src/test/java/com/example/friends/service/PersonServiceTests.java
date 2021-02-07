package com.example.friends.service;

import com.example.friends.domain.Block;
import com.example.friends.domain.Person;
import com.example.friends.domain.dto.Birthday;
import com.example.friends.repository.BlockRepository;
import com.example.friends.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class PersonServiceTests {
    @Autowired
    private  PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;
    
    @Test
    void getPeopleExcludeBlocks(){

        List<Person> result = personService.getPeopleExcludeBlocks();

        result.forEach(System.out::println);
    }

    @Test
    void getPeopleByName(){

        List<Person> result = personService.getPeopleByName("martin");

        result.forEach(System.out::println);
    }
    @Test
    void getPeopleByBloodType(){

        List<Person> result = personRepository.findByBloodType("AB");

        result.forEach(System.out::println);
    }
    @Test
    void getPeopleByBirthday(){

        List<Person> result = personRepository.findByBirthdayBetween(
                6,27);

        result.forEach(System.out::println);
    }
    @Test
    void cascadeTest(){
        List<Person> result = personRepository.findAll();

        result.forEach(System.out::println);

        Person person =result.get(0);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        personRepository.delete(person);
        blockRepository.findAll().forEach(System.out::println);
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
        personRepository.save(Person.builder()
                .name(name)
                .age(age)
                .bloodType(bloodType)
                .birthday(new Birthday(birthday))
                .build());
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = Person.builder()
                .name(name)
                .age(age)
                .bloodType(bloodType)
                .build();

        blockPerson.setBlock(Block.builder().name(name).build());

        personRepository.save(blockPerson);
    }


}