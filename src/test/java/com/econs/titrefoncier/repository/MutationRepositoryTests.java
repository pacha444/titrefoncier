package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MutationRepositoryTests {

    @Autowired
    MutationRepository mutationRepository;

    @Autowired
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;

    private Mutation mutation;

    private Mutation mutation1;

    @BeforeEach
    public void setup() {
        titreFoncier = TitreFoncier.builder()
                .numtitrefoncier("2345/DP")
                .volume("1")
                .natetconsistanceimmeuble("Terrain Urbain construit")
                .titrefoncierorigine("2344/DP")
                .numlot("20")
                .contenance("2000")
                .situation("Pikine")
                .titreradie(false)
                .build();
        tfenreg = titreFoncierRepository.save(titreFoncier);
        Date date = new Date("13/09/2021");
        mutation = Mutation.builder()
                .titrefoncier(tfenreg)
                .idborderauana("2")
                .dateacquisition(date)
                .modeacquisition("vente")
                .prixacquisition(15000000)
                .nomproprietaire("Lakhad Babou")
                .datedenaissance(new Date("05/03/1962"))
                .lieunaiss("Dakar")
                .profession("Commercant")
                .actuel("actuel")
                .build();
        mutation1 = Mutation.builder()
                .titrefoncier(tfenreg)
                .idborderauana("1")
                .dateacquisition(date)
                .modeacquisition("vente")
                .prixacquisition(100000)
                .nomproprietaire("Jérome du Valet")
                .datedenaissance(new Date("05/04/1906"))
                .lieunaiss("Dakar")
                .profession("Commercant")
                .actuel("ancien")
                .build();
        mutationRepository.save(mutation);
        mutationRepository.save(mutation1);
    }

    //Junit test pour tester la création d'une mutation pour un titre foncier existant
    @DisplayName("Junit test pour tester la création d'une mutation pour un titre foncier existant")
    @Test
    public void givenMutationavectfassocie_whenMutationcree_thenRetournerlamutationcreee() {
        //given-precondition or setup mutation créé dans setup
        //when-action or the behaviour we are going test
        Mutation SavedMutation = mutationRepository.save(mutation);
        //then-verify the output
        assertThat(SavedMutation).isNotNull();
    }

   @DisplayName("Junit test pour tester la récupération de plusieurs mutations pour un T.F. donné")
    @Test
    public void givenPlrsMutationsavecTF_whenMutationsExtraites_thenVerifierListMutationExtraite() {
        //given-precondition or setup mutation créé dans setup

        //when-action or the behaviour we are going test
        String numtitrefoncier=tfenreg.getNumtitrefoncier();
     // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<Mutation> mutationList=mutationRepository.findBynumtitrefoncier(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(mutationList.size()).isEqualTo(2);

    }

    @DisplayName("Junit test pour tester la récupération de tous les mutations concernant un contribuable")
    @Test
    public void givenNomPersonne_whenMutationsExtraites_thenVerifierListeMutations() {
        //given-precondition or setup mutation créé dans setup
        String name="Lakhad Babou";
        //when-action or the behaviour we are going test
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<Mutation> mutationList=mutationRepository.findBynomproprietaire(name);



        //then-verify the output
        assertThat(mutationList).isNotNull();
        assertThat(mutationList.size()).isEqualTo(1);
    }

    @DisplayName("Junit test pour tester la mise à jour du statut d'une personne pour un T.F. donné")
    @Test
    public void givenNomPersonne_whenMutationExtraites_thenVerifierChangementStatut() {
        //given-precondition or setup mutation créé dans setup
        String name="Lakhad Babou";
        String titrefoncier="2345/DP";
        //when-action or the behaviour we are going test
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<Mutation> mutationList=mutationRepository.findBynumtitrefoncieretproprietaire(titrefoncier,name);

        //then-verify the output
        assertThat(mutationList).isNotNull();

    }

}
