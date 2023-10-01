package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;

import java.util.List;

public interface MutationService {

    Mutation enregistrermutation(Mutation mutation, TitreFoncier titreFoncier);

    List<Mutation> recherchermutationspartitrefoncier(String numtitrefoncier);

    List<Mutation> recherchermutationsparproprietaire(String name);

    List<Mutation> recherchermutationsparproprietaireettitrefoncier(String name, String titrefoncier);

    void delete(Mutation mutation);
}
