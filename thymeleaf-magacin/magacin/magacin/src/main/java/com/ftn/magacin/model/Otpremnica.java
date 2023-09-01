package com.ftn.magacin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "otpremnica")
public class Otpremnica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firma")
    private String firma;

    @Column(name = "kolicina")
    private Long kolicina;

    @Column(name = "cena")
    private Double cena;

    @Column(name = "ukupna_cena")
    private Double ukupnaCena;

    @Column(name = "vreme")
    private Instant vreme;

    @Column(name = "storno")
    private Boolean storno;

    @ManyToOne
    @JsonIgnoreProperties(value = { "otpremnice" }, allowSetters = true)
    private Predmet predmet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Long getKolicina() {
        return kolicina;
    }

    public void setKolicina(Long kolicina) {
        this.kolicina = kolicina;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(Double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Instant getVreme() {
        return vreme;
    }

    public void setVreme(Instant vreme) {
        this.vreme = vreme;
    }

    public Boolean getStorno() {
        return storno;
    }

    public void setStorno(Boolean storno) {
        this.storno = storno;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}
