package com.grenciss.projdstr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "etudes")
public class Etude implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Getter
    @Setter
    @NotBlank
    @NotNull
    private String libelle;
    
    @Getter
    @Setter
    @NotBlank
    @NotNull
    private Date dateDebut;
    
    @Getter
    @Setter
    private Date dateFin;
    
    @Getter
    @Setter
    private Date dateAffectation;
    
    @Getter
    @Setter
    @NotNull
    @NotBlank
    private int budget;
    
    @Getter
    @Setter
    private String status;
    
    @Getter
    @Setter
    private String satisfaction;
    
    @Getter
    @Setter
    private String commentaire;

    public Etude() {
    }

    
}