package com.econs.titrefoncier.service.impl;

import com.econs.titrefoncier.model.CauseIndisponibilite;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.CauseIndisponibiliteRepository;
import com.econs.titrefoncier.service.CauseIndisponibiliteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CauseIndisponibiliteServiceImpl implements CauseIndisponibiliteService {

    private CauseIndisponibiliteRepository causeIndisponibiliteRepository;

    public CauseIndisponibiliteServiceImpl(CauseIndisponibiliteRepository causeIndisponibiliteRepository) {
        this.causeIndisponibiliteRepository = causeIndisponibiliteRepository;
    }

    /**
     * @param causeIndisponibilite
     * @param titreFoncier
     * @return
     */
    @Override
    public CauseIndisponibilite enregistrercauseindisponibilite(CauseIndisponibilite causeIndisponibilite, TitreFoncier titreFoncier) {
        causeIndisponibilite.setTitrefoncier(titreFoncier);
        return causeIndisponibiliteRepository.save(causeIndisponibilite);
    }

    /**
     * @param numtitrefoncier
     * @return
     */
    @Override
    public List<CauseIndisponibilite> recherchercausesindisponibilitepouruntf(String numtitrefoncier) {
        return causeIndisponibiliteRepository.findBynumtitrefoncier(numtitrefoncier);
    }

    /**
     * @param causeIndisponibilite
     */
    @Override
    public void delete(CauseIndisponibilite causeIndisponibilite) {
        causeIndisponibiliteRepository.delete(causeIndisponibilite);

    }
}
