package com.example.demo.model.network.response;


import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderInfoApiResponse {

    //USerApiResponse 하위에 주문내역 가지고 있고 그 하위에는 item 목록 가지고 있다.
    public UserApiResponse userApiResponse;

}
