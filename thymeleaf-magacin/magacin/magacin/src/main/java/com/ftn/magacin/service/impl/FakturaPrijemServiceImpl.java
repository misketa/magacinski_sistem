package com.ftn.magacin.service.impl;

import com.ftn.magacin.model.FakturaPrijem;
import com.ftn.magacin.model.Predmet;
import com.ftn.magacin.repository.FakturaPrijemRepository;
import com.ftn.magacin.repository.PredmetRepository;
import com.ftn.magacin.service.FakturaPrijemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FakturaPrijemServiceImpl implements FakturaPrijemService {

    @Autowired
    FakturaPrijemRepository repository;

    @Autowired
    PredmetRepository predmetRepository;

    @Override
    public void storno(Long id) {
        Optional<FakturaPrijem> f = repository.findById(id);
        if(f.isPresent()){
            FakturaPrijem fp = f.get();
            fp.setStorno(Boolean.TRUE);
            repository.save(fp);

            Predmet p = predmetRepository.getOne(fp.getPredmet().getId());
            p.setKolicinaNaLageru(p.getKolicinaNaLageru() - fp.getKolicina());
            predmetRepository.save(p);
        }
    }

    @Override
    public void create(FakturaPrijem create) {
        create.setStorno(Boolean.FALSE);
        create.setUkupnaCena(create.getCena()*create.getKolicina());
        repository.save(create);

        Predmet p = predmetRepository.getOne(create.getPredmet().getId());
        p.setKolicinaNaLageru(p.getKolicinaNaLageru() + create.getKolicina());
        predmetRepository.save(p);
    }

    @Override
    public List<FakturaPrijem> findAll() {
        List<FakturaPrijem> list = repository.findAll();
        return list.stream().filter(o -> o.getStorno() == null || o.getStorno().equals(Boolean.FALSE)).collect(Collectors.toList());
    }
}
