package com.econs.titrefoncier.service;


import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.TitreFoncierRepository;
import com.econs.titrefoncier.service.impl.TitreFoncierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TitreFoncierServiceTests {

    @Mock
    private TitreFoncierRepository titreFoncierRepository;

    @InjectMocks
    private TitreFoncierServiceImpl titreFoncierService;

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
    @Test
    public void givenTitreFoncierObject_whenSaveTitreFoncierObject_thenReturnTitreFoncierObject() {
        //given-precondition or setup

        BDDMockito.given(titreFoncierRepository.save(titreFoncier)).willReturn(titreFoncier);

        //when-action or the behaviour we are going test

        TitreFoncier SavedTitreFoncier = titreFoncierService.EnregistrerTitreFoncier(titreFoncier);
        //then-verify the output
        assertThat(SavedTitreFoncier).isNotNull();

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
        BDDMockito.given(titreFoncierRepository.findAll()).willReturn(List.of(titreFoncier,titreFoncier2));
        //when-action or the behaviour we are going test

        List<TitreFoncier>   titreFoncierList=titreFoncierService.getAllTitreFoncier();
        //then-verify the output
        assertThat(titreFoncierList).isNotNull();
        assertThat(titreFoncierList.size()).isEqualTo(2);

    }

    //Junit test for
    @DisplayName("Méthode Junit pour tester une liste de T.F. vide")
    @Test
    public void givenEmptyList_whenRetrieveTitreFoncierList_thenReturnEmptyList() {
        //given-precondition or setup

        BDDMockito.given(titreFoncierRepository.findAll()).willReturn(Collections.emptyList());
        //when-action or the behaviour we are going test

        List<TitreFoncier>   titreFoncierList=titreFoncierService.getAllTitreFoncier();
        //then-verify the output
        assertThat(titreFoncierList).isEmpty();
        assertThat(titreFoncierList.size()).isEqualTo(0);

    }

    //Junit test for
    @DisplayName("Méthode Junit pour tester la récupération d'un T.F. par son numero T.F.")
    @Test
    public void givenTitreFoncier_whenRetrieveTitreFoncierByNumTF_thenReturnTitreFoncier() {
        //given-precondition or setup

        BDDMockito.given(titreFoncierRepository.findBynumtitrefoncier("2345/DP")).willReturn(Optional.of(titreFoncier));

        //when-action or the behaviour we are going test
        TitreFoncier titreFoncierdb = titreFoncierService.rechercheparnumerotitrefoncier(titreFoncier.getNumtitrefoncier()).get();
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
                .volume("1")
                .natetconsistanceimmeuble("Terrain Urbain construit")
                .titrefoncierorigine("2344/DP")
                .numlot("21")
                .contenance("1000")
                .situation("Pikine")
                .titreradie(false)
                .build();
        BDDMockito.given(titreFoncierRepository.findByVolume("1")).willReturn(Optional.of(List.of(titreFoncier,titreFoncier2)));

        // when -  action or the behaviour that we are going test
        List<TitreFoncier> titreFoncierList = titreFoncierService.rechercheparvolume("1").get();
        //then-verify the output
        assertThat(titreFoncierList.size()).isNotNull();
        assertThat(titreFoncierList.size()).isEqualTo(2);
    }

    //Junit test for
    @DisplayName("Junit test pour opération de mise à jour d'un T.F.")
    @Test
    public void givenTitreFoncier_whenUpdateTitreFoncier_thenTitreFoncierMAJ() {
        //given-precondition or setup

        BDDMockito.given(titreFoncierRepository.save(titreFoncier)).willReturn(titreFoncier);
        titreFoncier.setContenance("1500");
        //when-action or the behaviour we are going test

        TitreFoncier UpdatedTitreFoncier=titreFoncierService.EnregistrerTitreFoncier(titreFoncier);
        //then-verify the output
        assertThat(titreFoncier.getContenance()).isEqualTo("1500");


    }

    //Junit test for
    @DisplayName("Junit test pour opération de suppression d'un T.F.")
    @Test
    public void givenTitreFoncierObject_whenDelete_thenTitreFoncierRemoved() {
        //given-precondition or setup
        Integer titreFoncierId=1;
        BDDMockito.willDoNothing().given(titreFoncierRepository).deleteById(1);
        //when-action or the behaviour we are going test
        titreFoncierService.suppressionparidentifiant(titreFoncierId,titreFoncier);


        // then - verify the output
        verify(titreFoncierRepository,times(1)).deleteById(titreFoncierId);

    }

}
