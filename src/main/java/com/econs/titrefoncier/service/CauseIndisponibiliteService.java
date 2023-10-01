package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.CauseIndisponibilite;
import com.econs.titrefoncier.model.TitreFoncier;

import java.util.List;

public interface CauseIndisponibiliteService {

    CauseIndisponibilite enregistrercauseindisponibilite(CauseIndisponibilite causeIndisponibilite, TitreFoncier titreFoncier);

    List<CauseIndisponibilite> recherchercausesindisponibilitepouruntf(String numtitrefoncier);

    void delete(CauseIndisponibilite causeIndisponibilite);
}
