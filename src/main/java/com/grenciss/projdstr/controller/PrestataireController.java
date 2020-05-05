package com.grenciss.projdstr.controller;

import java.util.List;

import javax.validation.Valid;

import com.grenciss.projdstr.model.Prestataire;
import com.grenciss.projdstr.repository.PrestataireRepository;
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
@RequestMapping("/api/prestataires")
public class PrestataireController {
    
    @Autowired
    PrestataireRepository prestataires;

    @GetMapping("/all")
    public List<Prestataire> getAllPrestataires(){
        return prestataires.findAll();
    }

    @GetMapping("/get/{id}")
    public Prestataire getOneById(@PathVariable("id") long id) {
        return prestataires.findById(id).orElseThrow(()-> new ResourceNotFoundException("Prestataire", "id", id));
    }

    @PostMapping("/save")
    public Prestataire savePrestataire(@Valid @RequestBody Prestataire prestataire){
        return prestataires.save(prestataire);
    }

    @PutMapping("/update/{id}")
    public Prestataire updatePrestataire(@PathVariable(value = "id") Long id, @Valid @RequestBody Prestataire newPrestataire){
        Prestataire prestataire = prestataires.findById(id).orElseThrow(()-> new ResourceNotFoundException("Prestataire", "id", id));
        prestataire.update(newPrestataire);
        return prestataires.save(prestataire);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePrestataire(@PathVariable(value="id") Long id){
        Prestataire prestataire = prestataires.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", id));
        prestataires.delete(prestataire);
        return ResponseEntity.ok().build();
    }
}