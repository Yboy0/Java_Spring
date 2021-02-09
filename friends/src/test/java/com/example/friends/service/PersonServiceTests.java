package com.example.friends.service;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.domain.dto.Birthday;
import com.example.friends.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {
    @InjectMocks
    private  PersonService personService;

    @Mock
    private PersonRepository personRepository;

    
//    @Test
//    void getPeopleExcludeBlocks(){
//
//        List<Person> result = personService.getPeopleExcludeBlocks();
//
//        result.forEach(System.out::println);
//    }

    @Test
    void getPeopleByName(){
        when(personRepository.findByName("martin"))
                .thenReturn(Lists.newArrayList(Person.builder().name("martin").build()));

        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(1);

    }

    @Test
    void getPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(Person.builder().name("martin").build()));

        Person person = personService.getPerson(1L);

        assertThat(person.getName()).isEqualTo("martin");
    }

    @Test
    void getPersonIfNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        Person person = personService.getPerson(1L);

        assertThat(person).isNull();
    }

    @Test
    void postPerson(){
        PersonDto dto = PersonDto.builder()
                .name("martin")
                .hobby("programming")
                .address("suwon")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .job("programmer")
                .build();

        personService.postPerson(dto);

        //실제로 save란 함수가 실행되었는지 확인
        verify(personRepository).save(argThat(new IsPersonWillBeInserted()));
    }
    @Test
    void modifyIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personService.modifyPerson(1L,mockPersonDto()));

    }
    @Test
    void modifyIfNameIsDifferent(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(Person.builder().name("tony").build()));

        assertThrows(RuntimeException.class, () -> personService.modifyPerson(1L,mockPersonDto()));

    }
    @Test
    void modifyPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(Person.builder().name("martin").build()));

        personService.modifyPerson(1L,mockPersonDto());

        //verify(personRepository,times(1)).save(any(Person.class));
        verify(personRepository,times(1)).save(argThat(new IsPersonWillBeUpdated()));
    }

    @Test
    void modifyByNameIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personService.modifyPerson(1L,"demian"));

    }

    @Test
    void modifyByName(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(Person.builder()
                        .name("martin")
                        .hobby("programming")
                        .address("suwon")
                        .phoneNumber("010-9283-6657")
                        .job("programmer")
                        .build()));
        personService.modifyPerson(1L,"demian");

        verify(personRepository,times(1)).save(argThat(new IsNameWillBeUpdated()));
    }

    @Test
    void deleteIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personService.deletePerson(1L));
    }

    @Test
    void deletePerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(Person.builder().name("martin").build()));

        personService.deletePerson(1L);

        verify(personRepository,times(1)).save(argThat(new IsPersonWillBeDeleted()));
    }



    @Test
    void getPeopleByBirthday(){

        List<Person> result = personRepository.findByBirthdayBetween(
                6,27);

        result.forEach(System.out::println);
    }

    private PersonDto mockPersonDto(){
        return   PersonDto.builder()
                .name("martin")
                .hobby("programming")
                .address("suwon")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .job("programmer")
                .build();
    }

    private static class IsPersonWillBeInserted implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"programming")
                    && equals(person.getAddress(),"suwon")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"programmer")
                    && equals(person.getPhoneNumber(),"010-9283-6657");
        }

        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsPersonWillBeUpdated implements ArgumentMatcher<Person> {

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"programming")
                    && equals(person.getAddress(),"판교")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"programmer")
                    && equals(person.getPhoneNumber(),"010-9283-6657");
        }

        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsNameWillBeUpdated implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.getName().equals("demian");
        }
    }

    private static class IsPersonWillBeDeleted implements ArgumentMatcher<Person>{
        @Override
        public boolean matches(Person person){
            return person.isDeleted();
        }
    }





}