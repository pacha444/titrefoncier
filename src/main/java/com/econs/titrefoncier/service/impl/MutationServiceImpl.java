package com.econs.titrefoncier.service.impl;

import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.MutationRepository;
import com.econs.titrefoncier.service.MutationService;

import java.util.List;

public class MutationServiceImpl implements MutationService {

    private MutationRepository mutationRepository;

    public MutationServiceImpl(MutationRepository mutationRepository) {
        this.mutationRepository = mutationRepository;
    }

    /**
     * @param mutation
     * @return
     */
    @Override
    public Mutation enregistrermutation(Mutation mutation, TitreFoncier titreFoncier) {
        mutation.setTitrefoncier(titreFoncier);
        return mutationRepository.save(mutation) ;
    }

    /**
     * @param numtitrefoncier
     * @return
     */
    @Override
    public List<Mutation> recherchermutationspartitrefoncier(String numtitrefoncier) {
        return mutationRepository.findBynumtitrefoncier(numtitrefoncier);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public List<Mutation> recherchermutationsparproprietaire(String name) {
        return mutationRepository.findBynomproprietaire(name);
    }

    /**
     * @param name
     * @param titrefoncier
     * @return
     */
    @Override
    public List<Mutation> recherchermutationsparproprietaireettitrefoncier(String name, String titrefoncier) {
        return mutationRepository.findBynumtitrefoncieretproprietaire(name,titrefoncier);
    }

    /**
     * @param mutation
     */
    @Override
    public void delete(Mutation mutation) {

        mutationRepository.delete(mutation);

    }


}
