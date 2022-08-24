package com.up.KGLSol.controller.api;


import com.up.KGLSol.entity.Client;
import com.up.KGLSol.exception.ClientNotFoundException;
import com.up.KGLSol.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

@RestController()
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    ClientService clientService;



    @PostMapping("/add")
    private ModelAndView addClient(@ModelAttribute ("client") Client client){
        ModelAndView createClientForm = new ModelAndView("client-add-form-view");
        String addingResponse = "Client successfully added";
        try{
            clientService.save(client);
        }catch (ConstraintViolationException ex){
            for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
                addingResponse = constraintViolation.getMessageTemplate();
            }

        }
        createClientForm.addObject("addingResponse",addingResponse);
        createClientForm.addObject("Client",new Client());
        return createClientForm;
    }


    @GetMapping("/add")
    private ModelAndView createClientForm(){
        ModelAndView createClientForm = new ModelAndView("client-add-form-view");
        Client client = new Client();
        createClientForm.addObject("Client",client);
        return createClientForm;
    }


    @GetMapping("/{id}/reservations")
    private ModelAndView showReservations(@PathVariable (name = "id") Long id){
        ModelAndView reservationsClientForm = new ModelAndView("client-reservations-form-view");
        Client client = clientService.findById(id).get();
        reservationsClientForm.addObject("Client",client);
        return reservationsClientForm;
    }


    @DeleteMapping("/{id}")
    private ModelAndView deleteClient(@PathVariable (name = "id") Long id){
        clientService.deleteById(id);
        return new ModelAndView("client-delete-view");

    }

    @PatchMapping ("/{id}/update")
    private ModelAndView updateClientInfo(
            @PathVariable (name = "id") Long id,
            @ModelAttribute ("client") Client client){
        ModelAndView updateClientForm = new ModelAndView("client-update-form-view");

        String updateResponse = "Client successfully updated";
        try{
            clientService.save(client);
        }catch (ConstraintViolationException ex){
            for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
                updateResponse = constraintViolation.getMessageTemplate();
            }

        }
        updateClientForm.addObject("updateResponse",updateResponse);

        updateClientForm.addObject("Client", client);

        return updateClientForm;
    }
    @GetMapping("/{id}/update")
    private ModelAndView updateClientForm(@PathVariable (name = "id") Long id){
        ModelAndView updateClientForm = new ModelAndView("client-update-form-view");
        Client client = clientService.findById(id).get();
        updateClientForm.addObject("Client",client);
        return updateClientForm;
    }
    @GetMapping("/all")
    private Iterable<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Client> getAllClients(@PathVariable Long id){
        return clientService.findById(id);
    }

}
