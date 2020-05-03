package com.grenciss.projdstr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "etudes")
public class Etude implements Serializable, Base{
    
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
    
    @NotNull
    @Column(nullable = false)
    private Date dateDebut;
    
    private Date dateFin;
    
    private Date dateAffectation;
    
    @NotNull
    @Column(nullable = false)
    private int budget;
    
    private String status;
    
    private String satisfaction;
    
    private String commentaire;

    private String typeEtude;

    public Etude() {}

    public Etude update(Object o){
        Etude p = (Etude)o;
        this.budget = p.budget;
        this.commentaire = p.commentaire;
        this.dateAffectation = p.dateAffectation;
        this.dateDebut = p.dateDebut;
        this.dateFin = p.dateFin;
        this.satisfaction = p.satisfaction;
        this.status = p.status;
        this.libelle = p.libelle;
        this.typeEtude = p.typeEtude;
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

    
}