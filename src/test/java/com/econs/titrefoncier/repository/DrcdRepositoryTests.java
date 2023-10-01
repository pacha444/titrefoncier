package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.Drcd;
import com.econs.titrefoncier.model.Mutation;
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
public class DrcdRepositoryTests {

    @Autowired
    private DrcdRepository drcdRepository;

    @Autowired
    private TitreFoncierRepository titreFoncierRepository;

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
        //given-precondition or setup

        //when-action or the behaviour we are going test
        Drcd SavedDroitRCD = drcdRepository.save(drcd);
        //then-verify the output
        assertThat(SavedDroitRCD).isNotNull();


    }
    @DisplayName("Junit test pour tester la récupération de plusieurs drcds pour un T.F. donné")
    @Test
    public void givenPlrsDrcdavecTF_whenDrcdExtraits_thenVerifierListMutationExtraite() {
        //given-precondition or setup mutation créé dans setup

        //when-action or the behaviour we are going test
        String numtitrefoncier=tfenreg.getNumtitrefoncier();
        List<Drcd> drcdList =drcdRepository.findBynumtitrefoncier(numtitrefoncier);
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
        //when-action or the behaviour we are going test
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<Drcd> drcdList=drcdRepository.findBynumtitrefoncieretnumdrcd(titrefoncier,num);


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
