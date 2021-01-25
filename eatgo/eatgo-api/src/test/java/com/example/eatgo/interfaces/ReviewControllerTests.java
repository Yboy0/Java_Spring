package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void createWithValidAttributes() throws Exception {
        given(reviewService.addReview(any(), eq(123L))).willReturn(
                Review.builder()
                .id(123L)
                .name("JOKER")
                .score(3)
                .description("Good")
                .build()
        );


        mvc.perform(post("/restaurants/123/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"JOKER\", \"score\": 3, \"description\": \"Good\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/123/reviews/123"));
        verify(reviewService).addReview(any(), eq(123L));

    }

    @Test
    public void createWithInValidAttributes() throws Exception {
        mvc.perform(post("/restaurants/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
        //한 번도 호출 안하기 위해
        verify(reviewService,never()).addReview(any(), eq(1L));

    }
}
