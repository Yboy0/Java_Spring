package com.example.friends.service;


import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.exception.PersonNotFoundException;
import com.example.friends.exception.RenameNotPermittedException;
import com.example.friends.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


//    public List<Person> getPeopleExcludeBlocks(){
////        List<Person> people = personRepository.findAll();
//////        List<Block> blocks = blockRepository.findAll();
//////
//////        //차단 목록에서 차단된 사람 이름만 가져오기
//////        List<String> blockNames =
//////                blocks.stream().map(Block::getName)
//////                .collect(Collectors.toList());
////        //people이 갖고 있는 값 중에서 blockNames에 Person.getName()이 포함되어 있지 않을 때만 person 뽑기
//////        return people.stream()
//////                .filter(person -> !blockNames.contains(person.getName()))
//////                .collect(Collectors.toList());
////
////        //block data가 없는 person만 가져온다.
////        return people.stream().filter(person -> person.getBlock()==null)
////                .collect(Collectors.toList());
////        return personRepository.findByBlockIsNull();
//    }

    @Transactional
    public Person getPerson(Long id){
        //Person person = personRepository.findById(id).get();
        Person person = personRepository.findById(id).orElse(null);

        log.info("person: {}",person); // logback을 이용해 log 출력을 제한 할 수 있는 장점
                                        // sout보다 좋음 .
        return person;
    }

    @Transactional
    public void postPerson(PersonDto personDto) {
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());
        personRepository.save(person);
    }

    @Transactional
    public void modifyPerson(Long id, PersonDto personDto) {
        Person personDb = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);

        if(!personDb.getName().equals(personDto.getName())){
            throw new RenameNotPermittedException();
        }

        personDb.set(personDto);

    }

    //Person 이름만 바꾸는 로직
    @Transactional
    public void modifyPerson(Long id, String name) {
       Person person = personRepository.findById(id)
               .orElseThrow(PersonNotFoundException::new);

       person.setName(name);

       personRepository.save(person);

    }

    @Transactional
    public void deletePerson(Long id) {
//        Person person = personRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("아이디가 존재 하지 않습니다"));
//
//        personRepository.delete(person);

       // personRepository.deleteById(id);

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));

        person.setDeleted(true);

        personRepository.save(person);
    }
    public List<Person> getPeopleByName(String name) {
//        List<Person> people =  personRepository.findAll();
//
//        return people.stream().filter(person -> person.getName().equals(name))
//                .collect(Collectors.toList());
        return personRepository.findByName(name);
    }



}
