package com.ftn.magacin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "predmet")
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "kolicina_na_lageru")
    private Long kolicinaNaLageru;

    @Column(name = "pocetno_stanje")
    private Long pocetnoStanje;

    @OneToMany(mappedBy = "predmet")
    private Set<FakturaPrijem> prijemi = new HashSet<>();

    @OneToMany(mappedBy = "predmet")
    private Set<Otpremnica> otpremnice = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getKolicinaNaLageru() {
        return kolicinaNaLageru;
    }

    public void setKolicinaNaLageru(Long kolicinaNaLageru) {
        this.kolicinaNaLageru = kolicinaNaLageru;
    }

    public Long getPocetnoStanje() {
        return pocetnoStanje;
    }

    public void setPocetnoStanje(Long pocetnoStanje) {
        this.pocetnoStanje = pocetnoStanje;
    }

    public Set<FakturaPrijem> getPrijemi() {
        return prijemi;
    }

    public void setPrijemi(Set<FakturaPrijem> prijemi) {
        this.prijemi = prijemi;
    }

    public Set<Otpremnica> getOtpremnice() {
        return otpremnice;
    }

    public void setOtpremnice(Set<Otpremnica> otpremnice) {
        this.otpremnice = otpremnice;
    }
}
