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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymleafUIController {
    
    @Autowired
    PrestataireRepository prestataires;

    @Autowired
    EtudeRepository etudes;

    @GetMapping("/")
    public String index(Prestataire prestataire, Etude etude){
        return "index";
    }

    @GetMapping("/nouveau/prestataire")
    public String newPrestataireForm(Prestataire prestataire){
        return "form-prestataire";
    }

    @PostMapping("/save/prestataire")
    public String savePrestataire(@Valid Prestataire prestataire, BindingResult result, Model model){
        prestataires.save(prestataire);
        return "redirect:/liste/prestataires";
    }

    @GetMapping("/liste/prestataires")
    public String listPrestataire(Model model, Prestataire prestataire){
        List<Prestataire> listPrestataire = prestataires.findAll();
        model.addAttribute("prestataires", listPrestataire);
        return "liste-prestataire";
    }

    @GetMapping("/details/prestataire/{id}")
    public String detailsPrestataire(@PathVariable("id") long id ,Model model){
        
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
        return "details-prestataire";
    }

    @PostMapping("/update/prestataire/{id}")
    public String updatePrestataire(@PathVariable("id") long id, @Valid Prestataire p){
        Prestataire prestataire = prestataires.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", id));
        prestataire.update(p);
        prestataires.save(prestataire);
        return "redirect:/details/prestataire/"+prestataire.getId();
    }

    @GetMapping("/delete/prestataire/{id}")
    public String deletePrestataire(@PathVariable("id") long id){
        Prestataire prestataire = prestataires.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", id));
        prestataires.delete(prestataire);
        return "redirect:/liste/prestataires";
    }

    // ETUDE

    @PostMapping("/save/etude")
    public String saveEtude(@Valid Etude etude, BindingResult result, Model model){
        System.err.println(etude);
        Etude e = etudes.save(etude);
        return "redirect:/details/etude/"+e.getId();
    }

    @PostMapping("/save/etude/prestataire/{id}")
    public String saveEtudePrestataire(@PathVariable("id") long id, @RequestParam(value = "candidat") String c){
        Long candidat = Long.valueOf(c);
        Prestataire prestataire = prestataires.findById(candidat).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", candidat));
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        etude.getPrestataires().add(prestataire);
        etudes.save(etude);
        return "redirect:/details/etude/"+etude.getId();
    }

    @GetMapping("/liste/etudes")
    public String listeEtude(Model model, Etude etude){
        List<Etude> allEtudes = etudes.findAll();
        model.addAttribute("etudes", allEtudes);
        return "liste-etude";
    }

    @GetMapping("/details/etude/{id}")
    public String detailsEtude(@PathVariable("id") long id, Model model){
        List<Prestataire> allPrestataires = prestataires.findAll();
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        model.addAttribute("etude", etude);
        model.addAttribute("prestataires", allPrestataires);
        return "details-etude";
    }

    @PostMapping("/update/etude/{id}")
    public String updateEtude(@PathVariable(value = "id") Long id, @Valid Etude newEtude){
        Etude etude = etudes.findById(id).orElseThrow(()-> new ResourceNotFoundException("Etude", "id", id));
        etude.update(newEtude);
        etudes.save(etude);
        return "redirect:/details/etude/"+etude.getId();
    }

    @GetMapping("/delete/etude/{id}")
    public String deleteEtude(@PathVariable("id") long id){
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        etudes.delete(etude);
        return "redirect:/liste/etudes";
    }
}