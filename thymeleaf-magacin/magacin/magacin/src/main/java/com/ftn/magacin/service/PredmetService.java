package com.ftn.magacin.service;

import com.ftn.magacin.model.Predmet;

import java.util.List;

public interface PredmetService {
    List<Predmet> findAll();

    void create(Predmet create);
}
