package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.ModifConstImmeuble;
import com.econs.titrefoncier.model.TitreFoncier;

import java.util.List;

public interface ModifConstImmeubleService {

    ModifConstImmeuble enregistrerModificationConstitutionImmeuble(ModifConstImmeuble modifConstImmeuble, TitreFoncier titreFoncier);

    List<ModifConstImmeuble> recherchermodificationconstitutionimmeublepartf(String numtitrefoncier);

    List<ModifConstImmeuble> recherchermodificationconstitutionimmeublepartfettypedonne(String numtitrefoncier,String choix);

    void delete(ModifConstImmeuble modifConstImmeuble);
}
