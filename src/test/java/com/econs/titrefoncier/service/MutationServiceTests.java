package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.MutationRepository;
import com.econs.titrefoncier.repository.TitreFoncierRepository;
import com.econs.titrefoncier.service.impl.MutationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MutationServiceTests {
    @Mock
    private TitreFoncierRepository titreFoncierRepository;

    @Mock
    private MutationRepository mutationRepository;

    @InjectMocks
    private MutationServiceImpl mutationService;

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
        BDDMockito.given(mutationRepository.save(mutation)).willReturn(mutation);
        //when-action or the behaviour we are going test
        Mutation SavedMutation = mutationService.enregistrermutation(mutation,tfenreg);
        //then-verify the output
        assertThat(SavedMutation).isNotNull();
    }

    @DisplayName("Junit test pour tester la récupération de plusieurs mutations pour un T.F. donné")
    @Test
    public void givenPlrsMutationsavecTF_whenMutationsExtraites_thenVerifierListMutationExtraite() {
        //given-precondition or setup mutation créé dans setup
        String numtitrefoncier="2345/DP";
        BDDMockito.given(mutationRepository.findBynumtitrefoncier(numtitrefoncier)).willReturn(List.of(mutation,mutation1));

        //when-action or the behaviour we are going test
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<Mutation> mutationList=mutationService.recherchermutationspartitrefoncier(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(mutationList.size()).isEqualTo(2);

    }
    @DisplayName("Junit test pour tester la récupération titre fonciers appartenant à une personne")
    @Test
    public void givenNomPersonne_whenTitreFonciersExtraites_thenVerifierListeTitreFonciers() {
        //given-precondition or setup mutation créé dans setup
        String name="Lakhad Babou";
        BDDMockito.given(mutationRepository.findBynomproprietaire(name)).willReturn(List.of(mutation));
        //when-action or the behaviour we are going test



        List<Mutation> mutationList = mutationService.recherchermutationsparproprietaire(name);
        List<TitreFoncier> tfsduproprietaire = mutationList.stream()
                .map( c -> c.getTitrefoncier() )
                .collect( Collectors.toList() );
        //then-verify the output

        assertThat(mutationList.size()).isEqualTo(1);
        assertThat(tfsduproprietaire.size()).isEqualTo(1);
    }


    @DisplayName("Junit test pour tester la mise à jour du statut d'une personne pour un T.F. donné")
    @Test
    public void givenNomPersonne_whenMutationExtraites_thenVerifierChangementStatut() {
        //given-precondition or setup mutation créé dans setup
        String name="Lakhad Babou";
        String titrefoncier="2345/DP";
        BDDMockito.given(mutationRepository.findBynumtitrefoncieretproprietaire(titrefoncier,name)).willReturn(List.of(mutation));
        //when-action or the behaviour we are going test
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);

        List<Mutation> mutationList=mutationService.recherchermutationsparproprietaireettitrefoncier(titrefoncier,name);

        mutationList.stream()
                .filter(c->c.getNomproprietaire().equals("Lakhad Babou"))
                .map(c-> {
                    c.setActuel("Ancien");
                    return c;
                })
                .forEach(c->mutationRepository.save(c));

        //then-verify the output
        assertThat(mutationList.get(0).getActuel()).isEqualTo("Ancien");

    }
}
