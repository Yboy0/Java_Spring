package com.example.friends.service;

import com.example.friends.domain.Block;
import com.example.friends.domain.Person;
import com.example.friends.repository.BlockRepository;
import com.example.friends.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        givenPeople();

        List<Person> result = personService.getPeopleExcludeBlocks();

        result.forEach(System.out::println);
    }

    @Test
    void getPeopleByName(){
        givenPeople();

        List<Person> result = personService.getPeopleByName("martin");

        result.forEach(System.out::println);
    }
    @Test
    void getPeopleByBloodType(){
        givenPeople();

        List<Person> result = personRepository.findByBloodType("AB");

        result.forEach(System.out::println);
    }
    @Test
    void getPeopleByBirthday(){
        givenPeople();

        List<Person> result = personRepository.findByBirthdayBetween(
                LocalDate.of(1997,6,1),LocalDate.of(1997,6,30));

        result.forEach(System.out::println);
    }
    @Test
    void cascadeTest(){
        givenPeople();

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
    @Test
    void  getPerson(){
        givenPeople();

        Person person = personService.getPerson(3L);
    }
    private void givenPeople() {
        givenBlockPerson("martin",10,"A");
        givenPerson("smith",10,"AB");
        givenPerson("Jack",10,"O");
        givenPerson("Chris",10,"B");
        givenPerson("martin",11,"A");
        givenPerson("martin",15,"AB");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(Person.builder()
                .name(name)
                .age(age)
                .bloodType(bloodType)
                .birthday(LocalDate.of(1997,6,27))
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