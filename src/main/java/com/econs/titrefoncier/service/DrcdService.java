package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.Drcd;
import com.econs.titrefoncier.model.TitreFoncier;

import java.util.List;

public interface DrcdService {

    Drcd enregistrerdrcd(Drcd drcd, TitreFoncier titreFoncier);

    List<Drcd> rechercherdrcdpartitrefoncier(String numtitrefoncier);

    List<Drcd> rechercherdrcdpartitrefoncieretnumdrcd(String titrefoncier, Integer num);

    void delete(Drcd drcd);
}
