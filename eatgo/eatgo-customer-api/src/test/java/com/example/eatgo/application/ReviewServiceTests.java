package com.example.eatgo.application;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import com.example.eatgo.domain.ReviewRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReviewServiceTests {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockReviewRepository();

        reviewService = new ReviewService(reviewRepository);


    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("Yboy")
                .score(1)
                .description("BAD")
                .build());
        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);
    }

    @Test
    public void addReview(){
        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("Good")
                .build();
        reviewService.addReview(review,eq(1004L));

        verify(reviewRepository).save(any());
    }

}