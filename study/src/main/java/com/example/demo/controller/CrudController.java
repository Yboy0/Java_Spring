package com.example.demo.controller;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.network.Header;
import com.example.demo.service.BaseService;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@Component
public abstract class CrudController<Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected BaseService<Req,Res,Entity> baseService;


    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
