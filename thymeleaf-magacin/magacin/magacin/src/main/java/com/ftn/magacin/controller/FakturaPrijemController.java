package com.ftn.magacin.controller;

import com.ftn.magacin.model.FakturaPrijem;
import com.ftn.magacin.model.Predmet;
import com.ftn.magacin.service.FakturaPrijemService;
import com.ftn.magacin.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping(value = "/faktura-prijem")
public class FakturaPrijemController {

    @Autowired
    FakturaPrijemService service;

    @Autowired
    PredmetService predmetService;

    @GetMapping(value = "/create")
    public ModelAndView create() throws IOException {
        List<Predmet> predmeti = predmetService.findAll();

        ModelAndView response = new ModelAndView("fakturaCreate");
        response.addObject("create", new FakturaPrijem());
        response.addObject("predmeti", predmeti);

        return response;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@ModelAttribute FakturaPrijem create, HttpServletResponse response) throws IOException {
        create.setVreme(Instant.now());
        service.create(create);
        response.sendRedirect("/magacin/faktura-prijem/list");
        return null;
    }

    @GetMapping(value = "/list")
    public ModelAndView list() throws IOException {
        List<FakturaPrijem> list = service.findAll();
        ModelAndView response = new ModelAndView("fakture");
        response.addObject("list", list);
        return response;
    }

    @PostMapping(value = "/storno")
    public ModelAndView storno(@RequestParam("id") Long id) throws IOException {
        service.storno(id);
        return list();
    }


}
