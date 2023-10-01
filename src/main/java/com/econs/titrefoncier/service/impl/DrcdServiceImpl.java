package com.econs.titrefoncier.service.impl;

import com.econs.titrefoncier.model.Drcd;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.DrcdRepository;
import com.econs.titrefoncier.service.DrcdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrcdServiceImpl  implements DrcdService {

    private DrcdRepository drcdRepository;

    public DrcdServiceImpl(DrcdRepository drcdRepository) {
        this.drcdRepository = drcdRepository;
    }

    /**
     * @param drcd
     * @param titreFoncier
     * @return
     */
    @Override
    public Drcd enregistrerdrcd(Drcd drcd, TitreFoncier titreFoncier) {
        drcd.setTitrefoncier(titreFoncier);
        return drcdRepository.save(drcd) ;
    }

    /**
     * @param numtitrefoncier
     * @return
     */
    @Override
    public List<Drcd> rechercherdrcdpartitrefoncier(String numtitrefoncier) {
        return drcdRepository.findBynumtitrefoncier(numtitrefoncier);
    }

    /**
     * @param titrefoncier
     * @param num
     * @return
     */
    @Override
    public List<Drcd> rechercherdrcdpartitrefoncieretnumdrcd(String titrefoncier, Integer num) {
        return drcdRepository.findBynumtitrefoncieretnumdrcd(titrefoncier,num);
    }

    /**
     * @param drcd
     */
    @Override
    public void delete(Drcd drcd) {
        drcdRepository.delete(drcd);

    }
}
