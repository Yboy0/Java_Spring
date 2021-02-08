package com.example.friends.controller;

import com.example.friends.domain.Person;
import com.example.friends.repository.PersonRepository;
import com.example.friends.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PersonControllerTests {
    @Autowired
    private  PersonController personController;

    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }


    @Test
    public void getPerson() throws Exception{

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isOk());

    }
    @Test
    public void postPerson() throws  Exception{
        //content json 타입으로

        mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\": \"martin2\",\n" +
                        "  \"bloodType\": \"AB\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void modifyPerson() throws Exception{

        mockMvc.perform(put("/api/person/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content("{\"name\": \"martin1\"}")
                )
                .andExpect(status().isOk());

        Person modperson = personService.getPerson(1L);
        assertThat(modperson.getName()).isEqualTo("martin1");

    }

    @Test
    void modifyName() throws Exception {

        mockMvc.perform(patch("/api/person/1")
                .param("name","david"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(delete("/api/person/1"))
                .andExpect(status().isOk());

        //log.info("people deleted: {}",personRepository.findPeopleDeleted());
    }


}