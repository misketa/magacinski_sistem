package com.ftn.magacin.repository;

import com.ftn.magacin.model.Otpremnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpremnicaRepository extends JpaRepository<Otpremnica, Long> {
}
