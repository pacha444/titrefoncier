package com.econs.titrefoncier.service.impl;

import com.econs.titrefoncier.model.ModifConstImmeuble;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.ModifConstImmeubleRepository;
import com.econs.titrefoncier.service.ModifConstImmeubleService;

import java.util.List;

public class ModifConstImmeubleServiceImpl implements ModifConstImmeubleService {

    private ModifConstImmeubleRepository modifConstImmeubleRepository;

    public ModifConstImmeubleServiceImpl(ModifConstImmeubleRepository modifConstImmeubleRepository) {
        this.modifConstImmeubleRepository = modifConstImmeubleRepository;
    }

    @Override
    public ModifConstImmeuble enregistrerModificationConstitutionImmeuble(ModifConstImmeuble modifConstImmeuble, TitreFoncier titreFoncier) {
        modifConstImmeuble.setTitrefoncier(titreFoncier);
        return modifConstImmeubleRepository.save(modifConstImmeuble);
    }

    @Override
    public List<ModifConstImmeuble> recherchermodificationconstitutionimmeublepartf(String numtitrefoncier) {
        return modifConstImmeubleRepository.findBynumtitrefoncier(numtitrefoncier);
    }

    /**
     * @param numtitrefoncier
     * @return
     */
    @Override
    public List<ModifConstImmeuble> recherchermodificationconstitutionimmeublepartfettypedonne(String numtitrefoncier,String choix) {
        return modifConstImmeubleRepository.findBynumtitrefoncier(numtitrefoncier).stream()
                .filter(c->c.getTypemodconstimm().equals(choix))
                .toList();
    }

    /**
     * @param modifConstImmeuble
     */
    @Override
    public void delete(ModifConstImmeuble modifConstImmeuble) {
        modifConstImmeubleRepository.delete(modifConstImmeuble);

    }
}
