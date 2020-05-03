package com.grenciss.projdstr.model;

import java.io.Serializable;
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

@Entity
@Table(name="prestataires")
public class Prestataire implements Serializable, Base{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String email;
    
    @NotBlank
    @Column(nullable = false)
    private String libelle;

    private String siege;
    
    @NotBlank
    @Column(nullable = false)
    private String persAContacter1;
    
    private String persAContacter2;
    
    @NotBlank
    @Column(nullable = false)
    private String contact1;
    
    private String contact2;
    
    private String commentaire;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable
    private Set<Offre> offres = new HashSet<>();
    
    public Prestataire(){}

    @Override
    public Prestataire update(Object o) {
        Prestataire p = (Prestataire)o;
        this.siege = p.siege;
        this.email = p.email;
        this.libelle = p.libelle;
        this.contact1 = p.contact1;
        this.contact2 = p.contact2;
        this.commentaire = p.commentaire;
        this.persAContacter1 = p.persAContacter1;
        this.persAContacter2 = p.persAContacter2;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public String getPersAContacter1() {
        return persAContacter1;
    }

    public void setPersAContacter1(String persAContacter1) {
        this.persAContacter1 = persAContacter1;
    }

    public String getPersAContacter2() {
        return persAContacter2;
    }

    public void setPersAContacter2(String persAContacter2) {
        this.persAContacter2 = persAContacter2;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Set<Offre> getOffres() {
        return offres;
    }

    public void setOffres(Set<Offre> offres) {
        this.offres = offres;
    }

    
}