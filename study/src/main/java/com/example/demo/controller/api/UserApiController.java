package com.example.demo.controller.api;


import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {


    @Override
    @PostMapping("") // api/user
    public Header create() {
        return null;
    }

    @Override
    @GetMapping("{id}") // api/user/{id}
    public Header read(@PathVariable(name ="id") Long id) {
        return null;
    }

    @Override
    @PutMapping("") // api/user
    public Header update() {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // api/user/{id}
    public Header delete(Long id) {
        return null;
    }
}
