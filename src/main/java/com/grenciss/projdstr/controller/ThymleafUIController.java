package com.grenciss.projdstr.controller;

import java.util.List;

import javax.validation.Valid;

import com.grenciss.projdstr.exception.ResourceNotFoundException;
import com.grenciss.projdstr.model.Prestataire;
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

    @GetMapping("/detail/prestataire/{id}")
    public String detailPrestataire(@PathVariable("id") long id ,Model model){
        Prestataire p = prestataires.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", id));
        model.addAttribute("p", p);
        return "detail-prestataire";
    }
}