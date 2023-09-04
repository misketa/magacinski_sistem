package com.ftn.magacin.service.impl;

import com.ftn.magacin.model.Predmet;
import com.ftn.magacin.repository.PredmetRepository;
import com.ftn.magacin.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredmetServiceImpl implements PredmetService {

    @Autowired
    PredmetRepository repository;

    @Override
    public List<Predmet> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(Predmet create) {
        create.setKolicinaNaLageru(create.getPocetnoStanje());
        repository.save(create);
    }
}
