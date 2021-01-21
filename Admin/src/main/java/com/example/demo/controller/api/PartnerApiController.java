package com.example.demo.controller.api;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.network.request.PartnerApiRequest;
import com.example.demo.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api.partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
