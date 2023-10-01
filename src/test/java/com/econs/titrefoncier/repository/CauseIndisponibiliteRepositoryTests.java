package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.CauseIndisponibilite;
import com.econs.titrefoncier.model.ModifConstImmeuble;
import com.econs.titrefoncier.model.TitreFoncier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CauseIndisponibiliteRepositoryTests {

    @Autowired
    private CauseIndisponibiliteRepository causeIndisponibiliteRepository;

    @Autowired
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;

    private CauseIndisponibilite causeIndisponibilite;
    private CauseIndisponibilite causeIndisponibilitea;
    private CauseIndisponibilite causeIndisponibiliteb;

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
        tfenreg=titreFoncierRepository.save(titreFoncier);
        causeIndisponibilite=CauseIndisponibilite.builder()
                .indclausesconv("Pour cause d'utilité publique")
                .dateinscription(new Date("05/02/1990"))
                .dateliberation(new Date("04/02/1992"))
                .numborderauanalytiqueins("01")
                .numborderauanalytiquelib("05")
                .titrefoncier(tfenreg)
                .build();
        causeIndisponibiliteb=CauseIndisponibilite.builder()
                .indclausesconv("Pour cause d'utilité publique")
                .dateinscription(new Date("05/02/1990"))
                .dateliberation(new Date("04/02/1992"))
                .numborderauanalytiqueins("01")
                .numborderauanalytiquelib("05")
                .titrefoncier(tfenreg)
                .build();
    }

    //Junit test for
    @DisplayName("Méthode Junit pour enregistrer une cause d'indisponibilité")
    @Test
    public void givenCauseIndisponibiliteObject_whenSave_thenReturnSavedCauseIndisponibilite() {
        //given-precondition or setup

        //when-action or the behaviour we are going test
        CauseIndisponibilite savedCauseIndisponibilite=causeIndisponibiliteRepository.save(causeIndisponibilite);
        //then-verify the output
        assertThat(savedCauseIndisponibilite).isNotNull();

    }
    @DisplayName("Junit test pour tester la récupération de plusieurs modifications pour un T.F. donné")
    @Test
    public void givenCausesIndisponibilitesavecTF_whenListCausesIndisponibilitesExtraite_thenVerifierListCauseIndisponibilite() {
        //given-precondition or setup mutation créé dans setup
        causeIndisponibiliteRepository.save(causeIndisponibilite);
        causeIndisponibiliteRepository.save(causeIndisponibiliteb);

        //when-action or the behaviour we are going test
        String numtitrefoncier=tfenreg.getNumtitrefoncier();
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<CauseIndisponibilite> modifConstImmeubleList=causeIndisponibiliteRepository.findBynumtitrefoncier(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(modifConstImmeubleList.size()).isEqualTo(2);

    }


}
