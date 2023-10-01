package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.TitreFoncier;

import java.util.List;
import java.util.Optional;

public interface TitreFoncierService {


    TitreFoncier EnregistrerTitreFoncier(TitreFoncier titreFoncier);

    List<TitreFoncier> getAllTitreFoncier();

    Optional<TitreFoncier> rechercheparnumerotitrefoncier(String numtitrefoncier);

    Optional<List<TitreFoncier>> rechercheparvolume(String s);


    void suppressionparidentifiant(Integer idtitrefoncier, TitreFoncier titreFoncier);
}
