package com.example.friends.controller;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.exception.PersonNotFoundException;
import com.example.friends.exception.RenameNotPermittedException;
import com.example.friends.exception.dto.ErrorResponse;
import com.example.friends.repository.PersonRepository;
import com.example.friends.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}") //http://localhost:8080/api/person/1
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //성공일 경우에 201번으로 return
    public void postPerson(@RequestBody PersonDto personDto) {
        personService.postPerson(personDto);
        //log.info("person -> {}", personRepository.findAll());
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {

        personService.modifyPerson(id, personDto);
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name) {

            personService.modifyPerson(id, name);

    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {

        personService.deletePerson(id);

        log.info("person -> {}", personRepository.findById(id));

    }

    @ExceptionHandler(value = RenameNotPermittedException.class)
    public ResponseEntity<ErrorResponse> handleRenameNoPermittedException(RenameNotPermittedException ex){
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(PersonNotFoundException ex){
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    //위의 exception handler중에서 가장 적합한 것을 handling하게 되고 만약 두 개다 아니라면 이쪽으로 handling이 진행 된다.
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
        log.error("서버오류: {}",ex.getMessage(),ex);
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 오류가 발생하였습니다"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
