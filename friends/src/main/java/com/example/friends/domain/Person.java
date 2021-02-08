package com.example.friends.domain;

import com.example.friends.controller.dto.PersonDto;
import com.example.friends.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
@Where(clause = "deleted = false")
public class Person {
//8 atr
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0") //이 값이 true가 되면 삭제가 되었다 간주
    private boolean deleted;

//    /*{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}*/
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
//    private Block block;

    public void set(PersonDto persnoDto){
        if(persnoDto.getHobby() != null){
            this.setHobby(persnoDto.getHobby());
        }
        if(persnoDto.getAddress() != null){
            this.setAddress(persnoDto.getAddress());
        }
        if(persnoDto.getJob() != null){
            this.setAddress(persnoDto.getJob());
        }
        if(persnoDto.getPhoneNumber() != null){
            this.setPhoneNumber(persnoDto.getPhoneNumber());
        }
        if(persnoDto.getBirthday() != null){
            this.setBirthday(Birthday.of(persnoDto.getBirthday()));
        }
    }

    public Integer getAge(){
        if(this.birthday != null){
            return LocalDate.now().getYear()-this.birthday.getYearOfBirthday() + 1;
        }else{
            return null;
        }
    }

    public boolean isBirthdayToday(){
        return LocalDate.now()
                .equals(LocalDate.of(this.birthday.getYearOfBirthday(),this.birthday.getMonthOfBirthday(),this.birthday.getDayOfBirthday()));

    }

}
