package com.example.friends.configuration.serializer;

import com.example.friends.domain.dto.Birthday;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

//1997-06-27 이런식으로 만들기위해서
public class BirthdaySerializer extends JsonSerializer<Birthday> {


    @Override
    public void serialize(Birthday value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      if(value != null) {
          gen.writeObject(LocalDate.of(value.getYearOfBirthday(), value.getDayOfBirthday(), value.getDayOfBirthday()));
      }
    }
}
