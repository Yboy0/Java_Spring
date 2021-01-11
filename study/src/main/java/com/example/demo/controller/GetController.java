package com.example.demo.controller;

import com.example.demo.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //Localhost:8080/api
public class GetController {
    @RequestMapping(method = RequestMethod.GET,path="/getMethod")//Localhost:8080/api/getmethod
    public String getRequest(){


        return "Hi getMethod";
    }
    @GetMapping("/getParameter") //Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id :"+id);
        System.out.println("password :"+password);

        return id+password;
    }
    //Localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //{"account":"","email":"","page":0}
        return searchParam;
    }



}
