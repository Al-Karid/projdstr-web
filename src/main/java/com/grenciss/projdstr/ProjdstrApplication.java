package com.grenciss.projdstr;

import com.grenciss.projdstr.model.Etude;
import com.grenciss.projdstr.model.Prestataire;
import com.grenciss.projdstr.repository.EtudeRepository;
import com.grenciss.projdstr.repository.PrestataireRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjdstrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjdstrApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadEtudeData(EtudeRepository etudes, PrestataireRepository prestataires){
		return (args) -> {
			// Save new Etude
			Etude etude = new Etude();
			etude.setLibelle("Achat de poulet");
			etude.setBudget(15000);
			etudes.save(etude);

			// Save Prestataire
			Prestataire prestataire = new Prestataire();
			prestataire.setLibelle("Agrics");
			prestataire.setEmail("agrics.sa@newcorp.com");
			prestataire.setSiege("Sassandra");
			prestataire.setPersAContacter1("San Alexys");
			prestataire.setContact1("+22554849532");
			prestataires.save(prestataire);

			Prestataire p = new Prestataire();
			p.setLibelle("FineTechs");
			p.setEmail("world.fine@finetechs.com");
			p.setSiege("Abobo Dockui");
			p.setPersAContacter1("Sahiri Samuel");
			p.setContact1("+22569321400");
			prestataires.save(p);
		};
	}
}
