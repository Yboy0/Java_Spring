package com.example.eatgo.interfaces;

import com.example.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userSevice;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";
//
//        User user = User.builder()
//                .id(1L)
//                .email(email)
//                .name(name)
//                .password(password)
//                .build();
        User user = userSevice.registerUser(email,name,password);


        String url = "/users/" + user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}


