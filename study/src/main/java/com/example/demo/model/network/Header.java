package com.example.demo.model.network;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor // 모든 매개변수 가지는 생성자
@Builder
public class Header<T> {

    // api 통신 시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private  String resultCode;

    // api 부가 설명
    private String description;

    private T data;

    private Pagination pagination;

    //OK
    public static<T> Header<T> OK(){
        return (Header<T>) Header.builder()
               .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    //Data OK
    public static<T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
               .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    public static<T> Header<T> OK(T data,Pagination pagination){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }
    //Error
    public static<T> Header<T> Error(String des){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("Error")
                .description(des)
                .build();
    }

}
