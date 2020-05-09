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
import org.springframework.data.domain.Sort;
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
    public String index(Prestataire prestataire, Etude etude, Model model){
        long nb_p = prestataires.count();
        long nb_e = etudes.count();
        List<Etude> e = etudes.findByRealisateur(0);
        long nb_a = e.size();
        
        model.addAttribute("nb_p", nb_p);
        model.addAttribute("nb_e", nb_e);
        model.addAttribute("nb_a", nb_a);
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
        List<Prestataire> listPrestataire = prestataires.findAll(Sort.by(Sort.Direction.ASC,"libelle"));
        model.addAttribute("prestataires", listPrestataire);
        return "liste-prestataire";
    }

    @GetMapping("/details/prestataire/{id}")
    public String detailsPrestataire(@PathVariable("id") long id ,Model model){
        
        Prestataire p = prestataires.findById(id).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", id));
        List<Etude> etudesPrestataire = etudes.findByRealisateur(id);

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
        model.addAttribute("realisations", etudesPrestataire);
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
        Etude e = etudes.save(etude);
        return "redirect:/details/etude/"+e.getId();
    }

    @PostMapping("/save/etude/candidats/{id}")
    public String saveEtudeCandidats(@PathVariable("id") long id, @RequestParam(value = "candidat") String c){
        Long candidat = Long.valueOf(c);
        Prestataire prestataire = prestataires.findById(candidat).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", candidat));
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        etude.getPrestataires().add(prestataire);
        etudes.save(etude);
        return "redirect:/details/etude/"+etude.getId();
    }

    @PostMapping("/save/etude/realisateur/{id}")
    public String saveEtudeRealisateur(@PathVariable("id") long id, @RequestParam(value = "realisateur") String r){
        Long realisateur = Long.valueOf(r);
        Prestataire prestataire = prestataires.findById(realisateur).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", realisateur));
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        etude.getPrestataires().add(prestataire);
        etude.setRealisateur(realisateur);
        etudes.save(etude);
        return "redirect:/details/etude/"+etude.getId();
    }

    @GetMapping("/liste/etudes")
    public String listeEtude(Model model, Etude etude){
        List<Etude> allEtudes = etudes.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("etudes", allEtudes);
        return "liste-etude";
    }

    @GetMapping("/details/etude/{id}")
    public String detailsEtude(@PathVariable("id") long id, Model model){
        List<Prestataire> allPrestataires = prestataires.findAll();
        Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
        Prestataire r;
        model.addAttribute("etude", etude);
        model.addAttribute("prestataires", allPrestataires);
        // Send realisateur if available
        if(etude.getRealisateur()!=0){
            long p = etude.getRealisateur();
            r = prestataires.findById(p).orElseThrow(() -> new ResourceNotFoundException("Prestataire", "id", p));
            model.addAttribute("realisateur", r);
        }else{
            r = new Prestataire();
            model.addAttribute("realisateur", r);
        }
        if (etude.getNiveauSatisfaction()==null) {
            model.addAttribute("dsp",0);
            model.addAttribute("qlt",0);
            model.addAttribute("dls",0);
            
        } 
        else {
            model.addAttribute("dsp",etude.getNiveauSatisfaction().split(",")[0]);
            model.addAttribute("qlt",etude.getNiveauSatisfaction().split(",")[1]);
            model.addAttribute("dls",etude.getNiveauSatisfaction().split(",")[2]);
        }
        return "details-etude";
    }

    @PostMapping("/update/etude/{id}")
    public String updateEtude(@PathVariable(value = "id") Long id, @Valid Etude newEtude){
        Etude etude = etudes.findById(id).orElseThrow(()-> new ResourceNotFoundException("Etude", "id", id));
        etude.update(newEtude);
        etudes.save(etude);
        return "redirect:/details/etude/"+etude.getId();
    }

    @PostMapping("/update/caracteristiques/etude/{id}")
    public String updateCaracteristiquesEtude(
        @PathVariable("id") long id,
        @RequestParam("disponibilite") String d,
        @RequestParam("qualite") String q,
        @RequestParam("delais") String delais){

            Etude etude = etudes.findById(id).orElseThrow(() -> new ResourceNotFoundException("Etude", "id", id));
            long dsp = Long.valueOf(d);
            long qlt = Long.valueOf(q);
            long dls = Long.valueOf(delais);
            float s =  (float) (0.2 * dsp + 0.5 * qlt + 0.3 * dls);
            etude.setNiveauSatisfaction(String.valueOf(dsp)+","+String.valueOf(qlt)+","+String.valueOf(dls));
            etude.setSatisfaction(String.valueOf(s));
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