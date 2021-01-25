package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource) throws URISyntaxException {

       Review review = reviewService.addReview(resource, restaurantId);

        String str = "/restaurants/" +restaurantId  +"/reviews/"+ review.getId();
        return ResponseEntity.created(
                new URI(str))
                .body("{}");
    }
}
