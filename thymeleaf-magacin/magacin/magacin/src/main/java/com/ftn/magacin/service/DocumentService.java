package com.ftn.magacin.service;

import java.io.ByteArrayInputStream;

public interface DocumentService {

    ByteArrayInputStream lagerLista(Long id);
    ByteArrayInputStream blankoPopis();
    ByteArrayInputStream magacinskaKartica();
}
