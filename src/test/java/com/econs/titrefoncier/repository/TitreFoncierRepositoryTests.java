package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.TitreFoncier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TitreFoncierRepositoryTests {

    @Autowired
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

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
    }


    //Junit test for
    @DisplayName("Méthode Junit pour enregistrer un T.F.")
    @Test
    public void givenTitreFoncierObject_whenSaveTitreFoncierObject_thenReturnSavedTitreFoncier() {
        //given-precondition or setup

        //when-action or the behaviour we are going test
        TitreFoncier savedTitreFoncier = titreFoncierRepository.save(titreFoncier);
        //then-verify the output
        assertThat(savedTitreFoncier).isNotNull();

    }

    //Junit test for
    @DisplayName("Méthode Junit pour tester la récupération de tous les T.F.")
    @Test
    public void givenSaveManyTitreFoncier_whenRetrieveTitreFoncierList_thenReturnTitreFoncierList() {
        //given-precondition or setup

        TitreFoncier titreFoncier2 = TitreFoncier.builder()
                .numtitrefoncier("2349/DP")
                .volume("1")
                .natetconsistanceimmeuble("Terrain Urbain construit")
                .titrefoncierorigine("2344/DP")
                .numlot("21")
                .contenance("1000")
                .situation("Pikine")
                .titreradie(false)
                .build();
        titreFoncierRepository.save(titreFoncier);
        titreFoncierRepository.save(titreFoncier2);

        //when-action or the behaviour we are going test

        List<TitreFoncier> titreFoncierList = titreFoncierRepository.findAll();

        //then-verify the output

        assertThat(titreFoncierList).isNotNull();
        assertThat(titreFoncierList.size()).isEqualTo(2);


    }

    //Junit test for
    @DisplayName("Méthode Junit pour tester la récupération d'un T.F. par son identifiant")
    @Test
    public void givenTitreFoncier_whenRetrieveTitreFoncierById_thenReturnTitreFoncier() {
        //given-precondition or setup

        titreFoncierRepository.save(titreFoncier);

        //when-action or the behaviour we are going test
        TitreFoncier titreFoncierdb = titreFoncierRepository.findById(titreFoncier.getIdtitrefoncier()).get();
        //then-verify the output
        assertThat(titreFoncierdb).isNotNull();
    }

    //Junit test for
    @DisplayName("Méthode Junit pour tester la récupération d'un T.F. par son numero T.F.")
    @Test
    public void givenTitreFoncier_whenRetrieveTitreFoncierByNumTF_thenReturnTitreFoncier() {
        //given-precondition or setup

        titreFoncierRepository.save(titreFoncier);

        //when-action or the behaviour we are going test
        Optional<TitreFoncier> titreFoncierdb = titreFoncierRepository.findBynumtitrefoncier(titreFoncier.getNumtitrefoncier());
        //then-verify the output
        assertThat(titreFoncierdb).isNotNull();
    }

    @DisplayName("JUnit test pour récupérer les titres fonciers par volume ")
    @Test
    public void givenListTitreFoncier_whenFindByVolume_thenReturnListTitreFoncierByVolume() {
        // given - precondition or setup
        //given-precondition or setup

        TitreFoncier titreFoncier2 = TitreFoncier.builder()
                .numtitrefoncier("2349/DP")
                .volume("2")
                .natetconsistanceimmeuble("Terrain Urbain construit")
                .titrefoncierorigine("2344/DP")
                .numlot("21")
                .contenance("1000")
                .situation("Pikine")
                .titreradie(false)
                .build();
        titreFoncierRepository.save(titreFoncier);
        titreFoncierRepository.save(titreFoncier2);
        // when -  action or the behaviour that we are going test
        List<TitreFoncier> titreFoncierList = titreFoncierRepository.findByVolume("1").get();
        //then-verify the output
        assertThat(titreFoncierList.size()).isNotNull();
        assertThat(titreFoncierList.size()).isEqualTo(1);
    }

    //Junit test for
    @DisplayName("Junit test pour opération de mise à jour d'un T.F.")
    @Test
    public void givenTitreFoncier_whenUpdateTitreFoncier_thenTitreFoncierMAJ() {
        //given-precondition or setup

        titreFoncierRepository.save(titreFoncier);
        //when-action or the behaviour we are going test
        titreFoncier.setContenance("1500");
        titreFoncierRepository.save(titreFoncier);
        //then-verify the output
        assertThat(titreFoncier.getContenance()).isEqualTo("1500");


    }

    //Junit test for
    @DisplayName("Junit test pour opération de suppression d'un T.F.")
    @Test
    public void givenTitreFoncierObject_whenDelete_thenTitreFoncierRemoved() {
        //given-precondition or setup

        titreFoncierRepository.save(titreFoncier);
        //when-action or the behaviour we are going test
        titreFoncierRepository.deleteById(titreFoncier.getIdtitrefoncier());
        Optional<TitreFoncier> titreFoncierOptional = titreFoncierRepository.findById(titreFoncier.getIdtitrefoncier());

        // then - verify the output
        assertThat(titreFoncierOptional).isEmpty();

    }

}
