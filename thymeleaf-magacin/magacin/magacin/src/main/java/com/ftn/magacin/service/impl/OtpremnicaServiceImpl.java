package com.ftn.magacin.service.impl;

import com.ftn.magacin.model.FakturaPrijem;
import com.ftn.magacin.model.Otpremnica;
import com.ftn.magacin.model.Predmet;
import com.ftn.magacin.repository.OtpremnicaRepository;
import com.ftn.magacin.repository.PredmetRepository;
import com.ftn.magacin.service.FakturaPrijemService;
import com.ftn.magacin.service.OtpremnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OtpremnicaServiceImpl implements OtpremnicaService {

    @Autowired
    OtpremnicaRepository repository;

    @Autowired
    PredmetRepository predmetRepository;

    @Override
    public void create(Otpremnica create) {
        create.setStorno(Boolean.FALSE);
        create.setUkupnaCena(create.getCena()*create.getKolicina());
        repository.save(create);

        Predmet p = predmetRepository.getOne(create.getPredmet().getId());
        p.setKolicinaNaLageru(p.getKolicinaNaLageru() - create.getKolicina());
        predmetRepository.save(p);
    }

    @Override
    public List<Otpremnica> findAll() {
        List<Otpremnica> list = repository.findAll();
        return list.stream().filter(o -> o.getStorno() == null || o.getStorno().equals(Boolean.FALSE)).collect(Collectors.toList());
    }

    @Override
    public void storno(Long id) {
        Optional<Otpremnica> o = repository.findById(id);
        if(o.isPresent()){
            Otpremnica oo = o.get();
            oo.setStorno(Boolean.TRUE);
            repository.save(oo);

            Predmet p = predmetRepository.getOne(oo.getPredmet().getId());
            p.setKolicinaNaLageru(p.getKolicinaNaLageru() + oo.getKolicina());
            predmetRepository.save(p);
        }
    }
}
