package com.grenciss.projdstr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "etudes")
public class Etude implements Serializable, Base {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String libelle;

    @DateTimeFormat(pattern="yyyy/mm/dd")
    private Date dateDebut;
    
    @DateTimeFormat(pattern="yyyy/mm/dd")
    private Date dateFin;

    private Date dateAffectation;

    @NotNull
    @Column(nullable = false)
    private int budget;

    private long realisateur;

    private String status;

    private String satisfaction;

    private String commentaire;

    private String typeEtude;

    private String periodicite;

    /**
     * String containing: Disponibilite 1-5 Qualite livrable 1-5 Respect delais 1-5
     */
    private String niveauSatisfaction;

    public Etude() {
    }

    public Etude update(Object o) {
        Etude p = (Etude) o;
        this.budget = p.budget;
        this.status = p.status;
        this.dateFin = p.dateFin;
        this.libelle = p.libelle;
        this.dateDebut = p.dateDebut;
        this.typeEtude = p.typeEtude;
        this.commentaire = p.commentaire;
        this.periodicite = p.periodicite;
        this.satisfaction = p.satisfaction;
        this.dateAffectation = p.dateAffectation;
        this.niveauSatisfaction = p.niveauSatisfaction;
        return this;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getTypeEtude() {
        return typeEtude;
    }

    public void setTypeEtude(String typeEtude) {
        this.typeEtude = typeEtude;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getNiveauSatisfaction() {
        return niveauSatisfaction;
    }

    public void setNiveauSatisfaction(String niveauSatisfaction) {
        this.niveauSatisfaction = niveauSatisfaction;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable
    private Set<Prestataire> prestataires = new HashSet<>();

    public Set<Prestataire> getPrestataires() {
        return prestataires;
    }

    public void setPrestataires(Set<Prestataire> prestataires) {
        this.prestataires = prestataires;
    }

    public long getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(long realisateur) {
        this.realisateur = realisateur;
    }

    
}