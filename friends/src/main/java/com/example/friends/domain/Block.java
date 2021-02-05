package com.example.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String reason;

    private LocalDate startDate;

    private  LocalDate endDate;
}
