package com.up.KGLSol.controller.aop;

import com.up.KGLSol.service.ClientService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class showAllClientAdvice {

    @Autowired
    private ClientService clientService;

    @Before(value = "execution(* *(..))")
    public void showAllClients(){
        System.out.println(clientService.findAll());
    }

}
