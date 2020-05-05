package com.grenciss.projdstr.controller;

import javax.validation.Valid;

import com.grenciss.projdstr.exception.ResourceNotFoundException;
import com.grenciss.projdstr.model.Prestataire;
import com.grenciss.projdstr.repository.PrestataireRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/dstr")
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
}