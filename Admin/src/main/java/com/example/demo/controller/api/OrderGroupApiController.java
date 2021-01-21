package com.example.demo.controller.api;

import com.example.demo.controller.CrudController;
import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.OrderGroupApiRequest;
import com.example.demo.model.network.response.OrderGroupApiResponse;
import com.example.demo.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

}