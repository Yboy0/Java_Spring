package com.example.eatgo.application;

import com.example.eatgo.domain.Reservation;
import com.example.eatgo.domain.ReservationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReservationServiceTests {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation(){

        Long restaurantId = 1004L;
        Long userId = 1004L;
        String name = "John";
        String date = "2021-02-06";
        String time = "20:00";
        Integer partySize = 20;

        given(reservationRepository.save(any()))
                .will(invocation -> {
                    Reservation reservation = invocation.getArgument(0);
                    return reservation;
                });

        Reservation reservation = reservationService.addReservation(
                restaurantId,userId,name,date,time,partySize);

        assertThat(reservation.getName()).isEqualTo(name);

        verify(reservationRepository).save(reservation);

    }
}