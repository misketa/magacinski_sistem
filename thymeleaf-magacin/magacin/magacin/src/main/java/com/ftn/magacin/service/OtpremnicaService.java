package com.ftn.magacin.service;

import com.ftn.magacin.model.Otpremnica;

import java.util.List;

public interface OtpremnicaService {
    void create(Otpremnica create);

    List<Otpremnica> findAll();

    void storno(Long id);
}
