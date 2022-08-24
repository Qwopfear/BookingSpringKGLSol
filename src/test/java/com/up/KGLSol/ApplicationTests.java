package com.up.KGLSol;


import com.up.KGLSol.entity.Client;
import com.up.KGLSol.entity.Rentable;
import com.up.KGLSol.entity.Reservation;
import com.up.KGLSol.exception.ReservationIllegalException;
import com.up.KGLSol.service.ClientService;
import com.up.KGLSol.service.RentableService;
import com.up.KGLSol.service.ReservationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import javax.validation.constraints.AssertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTests {
	@Autowired
	private ClientService clientService;

	@Autowired
	private RentableService rentableService;

	@Autowired
	private ReservationService reservationService;

	@Test
	@PostConstruct
	public void	addingNewReservationTest() throws ReservationIllegalException, ParseException {
		List<Reservation> reservations = (List<Reservation>) reservationService.findAll();
		Reservation r = new Reservation();
		r.setClient(clientService.findById(1L).get());
		r.setRentable(rentableService.findById(1L).get());
		r.setRentStart(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-26"));
		r.setRentEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-28"));
//		r.checkReservationAllow(r.getRentable().getReservations());
		reservationService.save(r);
		assertEquals(reservations.size()+1, r.getId());//Specify what boolean value to return

	}
}