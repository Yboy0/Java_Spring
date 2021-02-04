package com.example.friends.service;


import com.example.friends.domain.Block;
import com.example.friends.domain.Person;
import com.example.friends.repository.BlockRepository;
import com.example.friends.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();
        List<Block> blocks = blockRepository.findAll();

        //차단 목록에서 차단된 사람 이름만 가져오기
        List<String> blockNames =
                blocks.stream().map(Block::getName)
                .collect(Collectors.toList());
        //people이 갖고 있는 값 중에서 blockNames에 Person.getName()이 포함되어 있지 않을 때만 person 뽑기
        return people.stream()
                .filter(person -> !blockNames.contains(person.getName()))
                .collect(Collectors.toList());
    }
}
