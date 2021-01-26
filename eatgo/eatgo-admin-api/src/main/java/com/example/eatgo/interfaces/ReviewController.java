package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.Review;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @JsonGetter("/reviews")
    public List<Review> list(@PathVariable Long restaurantId){
        List<Review> reviews = reviewService.getReviews();
        return reviews;
    }

}
