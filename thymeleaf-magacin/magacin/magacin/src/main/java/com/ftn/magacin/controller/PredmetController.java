package com.ftn.magacin.controller;

import com.ftn.magacin.model.Otpremnica;
import com.ftn.magacin.model.Predmet;
import com.ftn.magacin.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/predmet")
public class PredmetController {

    @Autowired
    PredmetService service;

    @GetMapping(value = "/list")
    public ModelAndView list() throws IOException {
        List<Predmet> list = service.findAll();
        ModelAndView response = new ModelAndView("predmeti");
        response.addObject("list", list);
        return response;
    }

    @GetMapping(value = "/create")
    public ModelAndView create() throws IOException {

        ModelAndView response = new ModelAndView("predmetCreate");
        response.addObject("create", new Predmet());

        return response;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@ModelAttribute Predmet create, HttpServletResponse response) throws IOException {
        service.create(create);
        response.sendRedirect("/magacin/predmet/list");
        return null;
    }
}
