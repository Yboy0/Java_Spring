package com.example.friends.controller;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.Person;
import com.example.friends.domain.dto.Birthday;
import com.example.friends.repository.PersonRepository;
import com.example.friends.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class PersonControllerTests {
    @Autowired
    private  PersonController personController;

    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;


    @BeforeEach
    void beforeEach(){

        mockMvc = MockMvcBuilders.standaloneSetup(personController).setMessageConverters(messageConverter).build();
    }


    @Test
    public void getPerson() throws Exception{

        mockMvc.perform(get("/api/person/1"))
                .andExpect(status().isOk())
                //return 되는 json객체에서 name attribute를 갖고 오겠다는 것
                .andExpect(jsonPath("$.name").value("martin"))
                .andExpect(jsonPath("$.hobby").isEmpty())
                .andExpect(jsonPath("$.address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1997-06-27"))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.birthdayToday").isBoolean());
        //jsonPath("$.name").value("martin") == assertThat(person.getName()).isEqualTo("martin")

    }
    private int getAge(int yearOfBirthday){
        return LocalDate.now().getYear() - yearOfBirthday+1;
    }
    @Test
    public void postPerson() throws Exception{
        //content json 타입으로
        PersonDto dto = PersonDto.builder()
                .name("martin")
                .hobby("programming")
                .address("suwon")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .job("programmer")
                .build();

        mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJsonString(dto)))
                .andExpect(status().isCreated());

        Person result = personRepository.findAll(Sort.by(Sort.Direction.DESC,"id"))
                    .get(0);

        assertAll(
//                () -> assertThat(result.getName()).isEqualTo("martin"),
//                () -> assertThat(result.getHobby()).isEqualTo("programming"),
//                () -> assertThat(result.getAddress()).isEqualTo("판교"),
//                () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
//                () -> assertThat(result.getJob()).isEqualTo("programmer"),
//                () -> assertThat(result.getPhoneNumber()).isEqualTo("010-9283-6657")
        );
    }

    @Test
    public void modifyPerson() throws Exception{

        PersonDto dto = PersonDto.builder()
                .name("martin")
                .hobby("programming")
                .address("판교")
                .birthday(LocalDate.now())
                .phoneNumber("010-9283-6657")
                .job("programmer")
                .build();

        mockMvc.perform(put("/api/person/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(toJsonString(dto)))
                .andExpect(status().isOk());

        Person modperson = personService.getPerson(1L);
        System.out.println(modperson.getAddress()+" "+modperson.getJob());
        assertAll(
                () -> assertThat(modperson.getName()).isEqualTo("martin"),
//                () -> assertThat(modperson.getHobby()).isEqualTo("programming"),
//                () -> assertThat(modperson.getAddress()).isEqualTo("판교"),
//                () -> assertThat(modperson.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
//                () -> assertThat(modperson.getJob()).isEqualTo("programmer"),
                () -> assertThat(modperson.getPhoneNumber()).isEqualTo("010-9283-6657")
        );

    }

    @Test
    public void modifyPersonIfNameDifferent() throws Exception{
        PersonDto dto = PersonDto.of("james","programming","판교",LocalDate.now(),"programmer","010-9283-6657");

        assertThrows(NestedServletException.class, () ->
            mockMvc.perform(put("/api/person/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(toJsonString(dto)))
                .andExpect(status().isOk()));

        Person modperson = personService.getPerson(1L);
        assertThat(modperson.getName()).isEqualTo("martin1");

    }

    @Test
    void modifyName() throws Exception {
        PersonDto dto = PersonDto.of("james","programming","판교",LocalDate.now(),"programmer","010-9283-6657");

        mockMvc.perform(patch("/api/person/1")
                .param("name","david"))
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("david");
    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(delete("/api/person/1"))
                .andExpect(status().isOk());

        //test에서 직접 repository를 호출해 해당 값이 삭제가 되었는지 확인
        assertTrue(personRepository.findPeopleDeleted().stream()
        .anyMatch(person -> person.getId().equals(1L)));

        //log.info("people deleted: {}",personRepository.findPeopleDeleted());
    }

    @Test
    void checkJsonString() throws JsonProcessingException {
        PersonDto dto = new PersonDto();
        dto.setName("martin");
        dto.setBirthday(LocalDate.now());
        dto.setAddress("판교");

        System.out.println(">>>" + toJsonString(dto));
    }

    // dto객체를 json타입으로 serialize하는 함수
    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }


}