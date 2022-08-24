package com.up.KGLSol.controller.api;

import com.up.KGLSol.entity.Client;
import com.up.KGLSol.entity.Rentable;
import com.up.KGLSol.service.RentableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

@RestController
@RequestMapping("api/rent")
public class RentableController {

    @Autowired
    private RentableService rentableService;

//    @PostMapping("/add")
//    private String createRentableObject(
//            @RequestParam (name = "name")  String name,
//            @RequestParam (name = "desc")  String desc,
//            @RequestParam (name = "price") long price,
//            @RequestParam (name = "area")  long area
//            ){
//        return String.valueOf(rentableService.save(new Rentable(name,desc,price,area)));
//    }





    @PostMapping("/add")
    private ModelAndView createRentableByModelObject(
           @ModelAttribute ("rentable") Rentable rentable
    ){
        ModelAndView createRentForm = new ModelAndView("rent-add-form-view");
        rentableService.save(rentable);
        String addingResponse = "Rentable object added";
        try{
            rentableService.save(rentable);
        }catch (ConstraintViolationException ex){
            for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
                addingResponse = constraintViolation.getMessageTemplate();
            }
//
        }
        createRentForm.addObject("addingResponse",addingResponse);
        createRentForm.addObject("Rentable",new Rentable());
        return createRentForm;
    }


    @GetMapping("/add")
    private ModelAndView createRentableObjectWithForm(){
        ModelAndView createRentForm = new ModelAndView("rent-add-form-view");
        Rentable rentable = new Rentable();
        createRentForm.addObject("Rentable",rentable);
        return createRentForm;
    }

    @PatchMapping ("/{id}/update")
    private ModelAndView updateClientInfo(
            @PathVariable (name = "id") Long id,
            @ModelAttribute ("rentable") Rentable rentable){
        ModelAndView updateClientForm = new ModelAndView("rent-update-form-view");
        String updateResponse = "Rentable object updated";
        rentableService.save(rentable);

        updateClientForm.addObject("Rentable", rentable);
        updateClientForm.addObject("updateResponse", updateResponse);

        return updateClientForm;
    }
    @GetMapping("/{id}/update")
    private ModelAndView updateClientForm(@PathVariable (name = "id") Long id){
        ModelAndView updateClientForm = new ModelAndView("rent-update-form-view");
        Rentable rentable = rentableService.findById(id).get();
        updateClientForm.addObject("Rentable",rentable);
        return updateClientForm;
    }

    @DeleteMapping("/{id}")
    private ModelAndView deleteClient(@PathVariable (name = "id") Long id){
        rentableService.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/{id}/reservations")
    private ModelAndView showReservations(@PathVariable (name = "id") Long id){
        ModelAndView reservationsRentableForm = new ModelAndView("rent-reservations-form-view");
        Rentable rentable = rentableService.findById(id).get();
        reservationsRentableForm.addObject("Rentable",rentable);
        return reservationsRentableForm;
    }

    @GetMapping("/all")
    private Iterable<Rentable> getAll(){
        return rentableService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Rentable> getRentable(@PathVariable Long id){
        return rentableService.findById(id);
    }
}
