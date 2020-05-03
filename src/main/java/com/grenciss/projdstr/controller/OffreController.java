package com.grenciss.projdstr.controller;

import java.util.List;

import javax.validation.Valid;

import com.grenciss.projdstr.model.Offre;
import com.grenciss.projdstr.repository.OffreRepository;
import com.grenciss.projdstr.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/offres")
public class OffreController {
    
    @Autowired
    OffreRepository offres;

    @GetMapping("/all")
    public List<Offre> getAllOffres(){
        return offres.findAll();
    }

    @PostMapping("/save")
    public Offre saveOffre(@Valid @RequestBody Offre offre){
        return offres.save(offre);
    }

    @PutMapping("/update/{id}")
    public Offre updateOffre(@PathVariable(value = "id") Long id, @Valid @RequestBody Offre newOffre){
        Offre offre = offres.findById(id).orElseThrow(()-> new ResourceNotFoundException("Offre", "id", id));
        offre.update(newOffre);
        return offres.save(offre);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOffre(@PathVariable(value="id") Long id){
        Offre offre = offres.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offre", "id", id));
        offres.delete(offre);
        return ResponseEntity.ok().build();
    }
}