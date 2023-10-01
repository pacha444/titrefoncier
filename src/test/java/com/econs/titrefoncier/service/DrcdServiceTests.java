package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.Drcd;
import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.DrcdRepository;
import com.econs.titrefoncier.repository.TitreFoncierRepository;
import com.econs.titrefoncier.service.impl.DrcdServiceImpl;
import com.econs.titrefoncier.service.impl.TitreFoncierServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DrcdServiceTests {

    @Mock
    private TitreFoncierRepository titreFoncierRepository;

    @Mock
    private DrcdRepository drcdRepository;

    @InjectMocks
    private DrcdServiceImpl drcdService;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;


    private Drcd drcd;

    private Drcd drcda;

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
        drcd = Drcd.builder()
                .titrefoncier(tfenreg)
                .dateinscription(new Date("14/07/1995"))
                .dateradiation(new Date("15/01/2000"))
                .prixdedroitscedes(10000000)
                .indchargeoudroitconst("Bail cédé par l'état du Sénégal")
                .numbordanalytiqueinscr("5")
                .numbordanalytiquerad("8")
                .build();
        drcda = Drcd.builder()
                .titrefoncier(tfenreg)
                .dateinscription(new Date("14/07/2000"))
                .dateradiation(new Date("15/01/2021"))
                .prixdedroitscedes(500000000)
                .indchargeoudroitconst("Bail cédé par l'état du Sénégal")
                .numbordanalytiqueinscr("11")
                .numbordanalytiquerad("13")
                .build();
        drcdRepository.save(drcd);
        drcdRepository.save(drcda);

    }

    //Junit test for
    @DisplayName("Junit test pour tester la création d'un DRCD pour un titre foncier existant")
    @Test
    public void givenDrcd_whenSaveDrcd_thenVerifierSavedDrcd() {

        //given-precondition or setup mutation créé dans setup
        BDDMockito.given(drcdRepository.save(drcd)).willReturn(drcd);
        //when-action or the behaviour we are going test
        Drcd SavedDrcd = drcdService.enregistrerdrcd(drcd,tfenreg);
        //then-verify the output
        assertThat(SavedDrcd).isNotNull();

    }
    @DisplayName("Junit test pour tester la récupération de plusieurs drcds pour un T.F. donné")
    @Test
    public void givenPlrsDrcdavecTF_whenDrcdExtraits_thenVerifierListMutationExtraite() {
        //given-precondition or setup mutation créé dans setup
        String numtitrefoncier="2345/DP";
        BDDMockito.given(drcdRepository.findBynumtitrefoncier(numtitrefoncier)).willReturn(List.of(drcd,drcda));

        //when-action or the behaviour we are going test

        List<Drcd> drcdList =drcdService.rechercherdrcdpartitrefoncier(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(drcdList).isNotNull();
        assertThat(drcdList.size()).isEqualTo(2);
    }
    @DisplayName("Junit test pour la mise à jour d'un DRCD")
    @Test
    public void giventitrefoncieretNumDRCD_whenDRCDMAJ_thenVerifierChangementDRCD() {
        //given-precondition or setup mutation créé dans setup
        String titrefoncier="2345/DP";
        Integer num=1;
        BDDMockito.given(drcdRepository.findBynumtitrefoncieretnumdrcd(titrefoncier,num)).willReturn(List.of(drcd));
        //when-action or the behaviour we are going test

        List<Drcd> drcdList=drcdService.rechercherdrcdpartitrefoncieretnumdrcd(titrefoncier,num);

        drcdList.stream()
                .map(c-> {
                    c.setPrixdedroitscedes(15000000);
                    return c;
                })
                .forEach(c->drcdRepository.save(c));

        //then-verify the output
        assertThat(drcdList.get(0).getPrixdedroitscedes()).isEqualTo(15000000);

    }
}
