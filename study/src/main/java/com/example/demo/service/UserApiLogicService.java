package com.example.demo.service;

import com.example.demo.controller.CrudController;
import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.entity.User;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.Pagination;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserApiLogicService extends BaseService<UserApiRequest,UserApiResponse,User> {



    // 1.request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // client에서 요청한 data를 request body로 받아서 data 들을 꺼내서 entity 객체 생성
        // 1.request data
        UserApiRequest userApiRequest = request.getData();

        // 2.User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
//                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);

        //생성된 데이터 -> UserApiResponse return
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id -> repository getOne, getById
        Optional<User> optional =baseRepository.findById(id);

        //user -> userApiResponse return
        return  optional
                .map(user -> response(user))
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet(
                        () -> Header.Error("데이터 없음")
                );



    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. data
        UserApiRequest userApiRequest = request.getData();

        //2. id -> user 데이터를 찾고
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3.data ->  update
            // id
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
            return user;
        })
        .map(user -> baseRepository.save(user)) //update -> newUser
        .map(user -> response(user)) //userApiResponse
        .map(Header::OK)
        .orElseGet(()-> Header.Error("데이터 없음"));
        // 4. userApiResponse

    }

    @Override
    public Header delete(Long id) {
        //1.id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        //2.repository -> delete
        return optional.map(user ->{
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()->Header.Error("데이터 없음"));


    }

    private  UserApiResponse response(User user){
        //user -> userResponse 객체 만들어서 return 해준다.

        UserApiResponse userApiResponse=UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
//                .registeredAt(user.getRegisteredAt())
//                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return userApiResponse;
    }

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList =users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());
        //List<UserApiResponse>
        //Header<List<UserApiResponse>>

        Pagination pagination  = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();
        return Header.OK(userApiResponseList);
    }
}
