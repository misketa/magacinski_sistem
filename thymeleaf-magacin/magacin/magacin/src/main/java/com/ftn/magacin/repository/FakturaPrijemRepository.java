package com.ftn.magacin.repository;

import com.ftn.magacin.model.FakturaPrijem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakturaPrijemRepository extends JpaRepository<FakturaPrijem, Long> {
}
