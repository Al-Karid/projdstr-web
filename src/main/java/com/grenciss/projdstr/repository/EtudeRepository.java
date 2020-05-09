package com.grenciss.projdstr.repository;

import java.util.List;

import com.grenciss.projdstr.model.Etude;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudeRepository extends JpaRepository<Etude, Long> {
    
    List<Etude> findByRealisateur(long id);
}