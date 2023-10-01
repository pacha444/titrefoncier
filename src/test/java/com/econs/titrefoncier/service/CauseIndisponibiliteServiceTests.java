package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.CauseIndisponibilite;
import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.CauseIndisponibiliteRepository;
import com.econs.titrefoncier.repository.TitreFoncierRepository;
import com.econs.titrefoncier.service.impl.CauseIndisponibiliteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CauseIndisponibiliteServiceTests {
    @Mock
    private CauseIndisponibiliteRepository causeIndisponibiliteRepository;

    @Mock
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;

    private CauseIndisponibilite causeIndisponibilite;
    private CauseIndisponibilite causeIndisponibilitea;

    @InjectMocks
    private CauseIndisponibiliteServiceImpl causeIndisponibiliteService;

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
        causeIndisponibilitea=CauseIndisponibilite.builder()
                .indclausesconv("Pour cause d'utilité publique")
                .dateinscription(new Date("05/03/1990"))
                .dateliberation(new Date("04/02/1992"))
                .numborderauanalytiqueins("01")
                .numborderauanalytiquelib("05")
                .titrefoncier(tfenreg)
                .build();
        causeIndisponibiliteRepository.save(causeIndisponibilite);
        causeIndisponibiliteRepository.save(causeIndisponibilitea);
    }
    //Junit test for
    @DisplayName("Méthode Junit pour enregistrer une cause d'indisponibilité")
    @Test
    public void givenCauseIndisponibiliteObject_whenSave_thenReturnSavedCauseIndisponibilite() {

        //given-precondition or setup mutation créé dans setup
        BDDMockito.given(causeIndisponibiliteRepository.save(causeIndisponibilite)).willReturn(causeIndisponibilite);
        //when-action or the behaviour we are going test
        CauseIndisponibilite savedCauseIndisponibilite=
                causeIndisponibiliteService.enregistrercauseindisponibilite(causeIndisponibilite,tfenreg);
        //then-verify the output
        assertThat(savedCauseIndisponibilite).isNotNull();

    }
    @DisplayName("Junit test pour tester la récupération de plusieurs modifications pour un T.F. donné")
    @Test
    public void givenCausesIndisponibilitesavecTF_whenListCausesIndisponibilitesExtraite_thenVerifierListCauseIndisponibilite() {
        //given-precondition or setup mutation créé dans setup

        String numtitrefoncier="2345/DP";
        BDDMockito.given(causeIndisponibiliteRepository.findBynumtitrefoncier(numtitrefoncier)).willReturn(List.of(causeIndisponibilite,causeIndisponibilitea));
        //when-action or the behaviour we are going test

        List<CauseIndisponibilite> modifConstImmeubleList=causeIndisponibiliteService.recherchercausesindisponibilitepouruntf(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(modifConstImmeubleList.size()).isEqualTo(2);

    }
}
