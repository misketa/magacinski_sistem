package com.ftn.magacin.service;

import com.ftn.magacin.model.FakturaPrijem;

import java.util.List;

public interface FakturaPrijemService {
    void storno(Long id);

    void create(FakturaPrijem create);

    List<FakturaPrijem> findAll();
}
