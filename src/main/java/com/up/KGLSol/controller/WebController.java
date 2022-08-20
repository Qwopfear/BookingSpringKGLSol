package com.up.KGLSol.controller;


import com.up.KGLSol.service.ClientService;
import com.up.KGLSol.service.RentableService;
import com.up.KGLSol.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class WebController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private RentableService rentableService;
    @Autowired
    private ReservationService reservationService;


    @RequestMapping()
    public ModelAndView welcomePage()
    {
        ModelAndView mainPage = new ModelAndView("main-view");
        System.out.println(clientService.findAll());
        mainPage.addObject("rentable",rentableService.findAll());
        mainPage.addObject("clients",clientService.findAll());
        mainPage.addObject("reservation",reservationService.findAll());
        return mainPage;
    }

}
