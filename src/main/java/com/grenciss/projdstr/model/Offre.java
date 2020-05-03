package com.grenciss.projdstr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "offres")
public class Offre implements Serializable, Base{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    @Column(nullable = false)
    private String libelle;
    
    @Column(nullable = false)
    private Date dateDebut;
    
    private Date dateFin;
    
    private Date dateAffectation;
    
    private String commentaire;

    @ManyToMany(mappedBy = "offres", fetch = FetchType.LAZY)
    private Set<Prestataire> prestataires = new HashSet<>();

    @Override
    public Offre update(Object o) {
        Offre p = (Offre)o;
        this.libelle = p.libelle;
        this.dateFin = p.dateFin;
        this.dateDebut = p.dateDebut;
        this.commentaire = p.commentaire;
        this.dateAffectation = p.dateAffectation;
        return p;
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Set<Prestataire> getPrestataires() {
        return prestataires;
    }

    public void setPrestataires(Set<Prestataire> prestataires) {
        this.prestataires = prestataires;
    }
    
    
}