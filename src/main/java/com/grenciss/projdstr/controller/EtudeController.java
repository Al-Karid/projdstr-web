package com.grenciss.projdstr.controller;

import java.util.List;

import javax.validation.Valid;

import com.grenciss.projdstr.model.Etude;
import com.grenciss.projdstr.repository.EtudeRepository;
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
@RequestMapping("/api/etudes")
public class EtudeController {
    
    @Autowired
    EtudeRepository etudes;

    @GetMapping("/all")
    public List<Etude> getAllEtudes(){
        return etudes.findAll();
    }

    @GetMapping("/get/{id}")
    public Etude getEtudeById(@PathVariable("id") long id){
        return etudes.findById(id).orElseThrow(()-> new ResourceNotFoundException("Etude", "id", id));
    }

    @PostMapping("/save")
    public Etude saveEtude(@Valid @RequestBody Etude etude){
        return etudes.save(etude);
    }

    @PutMapping("/update/{id}")
    public Etude updateEtude(@PathVariable(value = "id") Long id, @Valid @RequestBody Etude newEtude){
        Etude etude = etudes.findById(id).orElseThrow(()-> new ResourceNotFoundException("Etude", "id", id));
        etude.update(newEtude);
        return etudes.save(etude);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEtude(@PathVariable(value="id") Long id){
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        etudes.delete(etude);
        return ResponseEntity.ok().build();
    }
}