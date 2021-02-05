package com.example.friends.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data

//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode -> 값이 같은 객체 equals, hashcode 같은 걸로 처리하게 해줌

@AllArgsConstructor // 인자가 전체 변수를 포함하는 constructor
@NoArgsConstructor // 인자 없는 constructor
@Builder
public class Person {
//8 atr
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int age;

    private String bloodType;

    private String hobby;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    /*{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}*/
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private Block block;

}
