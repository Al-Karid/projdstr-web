package com.grenciss.projdstr.repository;

import com.grenciss.projdstr.model.Etude;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudeRepository extends JpaRepository<Etude, Long> {
    
}