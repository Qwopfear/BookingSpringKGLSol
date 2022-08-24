package com.up.KGLSol.controller.api;

import com.up.KGLSol.entity.Client;
import com.up.KGLSol.entity.Reservation;
import com.up.KGLSol.exception.ReservationIllegalException;
import com.up.KGLSol.service.ClientService;
import com.up.KGLSol.service.RentableService;
import com.up.KGLSol.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private RentableService rentableService;

    @GetMapping("/all")
    private List<Reservation> getAllReservation() {
        return (List<Reservation>) reservationService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping("/add")
    private ModelAndView createReservation(
            @RequestParam(name = "client_id") Long clientId,
            @RequestParam(name = "rent_id") Long rentId,
            @RequestParam(name = "rentStart") String from,
            @RequestParam(name = "rentEnd") String to
    ) {
        String addingResponse = "New reservation was successfully created";
        try {
           reservationService.save(clientService.findById(clientId).get(),
                    rentableService.findById(rentId).get(),from,to,new Reservation());
        } catch (Exception e){
            addingResponse = e.getMessage();
        }
        ModelAndView createReservationForm = new ModelAndView("reservation-add-form-view");
        createReservationForm.addObject("Reservation", new Reservation());
        createReservationForm.addObject("Clients", clientService.findAll());
        createReservationForm.addObject("RentableObjs", rentableService.findAll());
        createReservationForm.addObject("addingResponse",addingResponse);
        return createReservationForm;
    }

    @GetMapping("/add")
    private ModelAndView createReservationForm(){
        ModelAndView createReservationForm = new ModelAndView("reservation-add-form-view");
        createReservationForm.addObject("Reservation", new Reservation());
        createReservationForm.addObject("Clients", clientService.findAll());
        createReservationForm.addObject("RentableObjs", rentableService.findAll());
        System.out.println(((List<Client>) clientService.findAll()));
        if (((List<Client>) clientService.findAll()).isEmpty())
            return new ModelAndView("redirect:/");
        return createReservationForm;
    }


    @GetMapping("/{id}/update")
    private ModelAndView updateClientForm(@PathVariable (name = "id") Long id){
        ModelAndView reservationUpdateForm = new ModelAndView("reservation-update-form-view");
        Reservation reservation = reservationService.findById(id).get();
        reservationUpdateForm.addObject("Reservation",reservation);
        reservationUpdateForm.addObject("Clients", clientService.findAll());
        reservationUpdateForm.addObject("RentableObjs", rentableService.findAll());
        return reservationUpdateForm;
    }
    @PutMapping("/{id}/update")
    private ModelAndView updateReservation(
            @PathVariable Long id,
            @ModelAttribute ("reservation") Reservation reservation,
            @RequestParam(name = "client_id") Long clientId,
            @RequestParam(name = "rent_id") Long rentId,
            @RequestParam(name = "rentStart") String from,
            @RequestParam(name = "rentEnd") String to

    ){
        ModelAndView updateReservationForm = new ModelAndView("reservation-update-form-view");
        String updateResponse = "Reservation was successfully updated";

        try {
            reservationService.save(clientService.findById(clientId).get(),
                    rentableService.findById(rentId).get(),from,to,reservation);

        } catch (ReservationIllegalException e) {
            updateResponse = e.getMessage();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        updateReservationForm.addObject("Reservation", reservation);
        updateReservationForm.addObject("Clients", clientService.findAll());
        updateReservationForm.addObject("RentableObjs", rentableService.findAll());
        updateReservationForm.addObject("updateResponse", updateResponse);

        return updateReservationForm;
    }
    @DeleteMapping("/{id}")
    private ModelAndView deleteClient(@PathVariable (name = "id") Long id){
        reservationService.deleteById(id);
        return new ModelAndView("redirect:/");

    }

}
