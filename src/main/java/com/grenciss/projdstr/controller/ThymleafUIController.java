package com.grenciss.projdstr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.grenciss.projdstr.exception.ResourceNotFoundException;
import com.grenciss.projdstr.model.Etude;
import com.grenciss.projdstr.model.Prestataire;
import com.grenciss.projdstr.repository.EtudeRepository;
import com.grenciss.projdstr.repository.PrestataireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymleafUIController {
    
    @Autowired
    PrestataireRepository prestataires;

    @Autowired
    EtudeRepository etudes;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/nouveau/prestataire")
    public String newPrestataireForm(Prestataire prestataire){
        return "form-prestataire";
    }

    @PostMapping("/save/prestataire")
    public String savePrestataire(@Valid Prestataire prestataire, BindingResult result, Model model){
        prestataires.save(prestataire);
        return "redirect:/";
    }

    @GetMapping("/liste/prestataire")
    public String listPrestataire(Model model){
        List<Prestataire> listPrestataire = prestataires.findAll();
        model.addAttribute("prestataires", listPrestataire);
        return "liste-prestataire";
    }

    @GetMapping("/details/prestataire/{id}")
    public String detailPrestataire(@PathVariable("id") long id ,Model model){
        
        Prestataire p = prestataires.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", id));
        
        //Recuperation des etudes d'un prestataires
        List<Etude> allEtudes = etudes.findAll();
        List<Long> etudesId = new ArrayList<>();
        for (Etude etude : allEtudes) {
            Set<Prestataire> allPrestataires = etude.getPrestataires();
            for (Prestataire allP : allPrestataires) {
                if (allP.getId()==id) {
                    etudesId.add(etude.getId());
                }
            }
        }
        List<Etude> allPrestataireEtudes = new ArrayList<>();
        for (Long eid : etudesId) {
            allPrestataireEtudes.add(etudes.findById(eid).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", eid)));
        }
        
        
        model.addAttribute("p", p);
        model.addAttribute("etudes", allPrestataireEtudes);
        return "detail-prestataire";
    }

    @GetMapping("/liste/etude")
    public String listeEtude(Model model){
        List<Etude> allEtudes = etudes.findAll();
        model.addAttribute("etudes", allEtudes);
        return "liste-etude";
    }
}