package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.ModifConstImmeuble;
import com.econs.titrefoncier.model.PrivilegeEtHypotheque;
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
public class PrivilegeetHypothequeRepositoryTests {

    @Autowired
    private PrivilegeEtHypothequeRepository privilegeEtHypothequeRepository;

    @Autowired
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;

    private PrivilegeEtHypotheque privilegeEtHypotheque;
    private PrivilegeEtHypotheque privilegeEtHypothequea;

    private PrivilegeEtHypotheque privilegeEtHypothequeb;
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
       privilegeEtHypotheque = PrivilegeEtHypotheque.builder()
                .designationdudroitconstitue("Hypotheque")
                .titrefoncier(tfenreg)
                .dateinscription(new Date("01/02/1999"))
                .dateliberation(new Date("05/02/2010"))
                .numbordereauanalytiqueinscrip("001")
                .numbordereauanalytiqueliberation("002")
                .build();
        privilegeEtHypothequea = PrivilegeEtHypotheque.builder()
                .designationdudroitconstitue("Hypotheque")
                .titrefoncier(tfenreg)
                .dateinscription(new Date("01/02/2010"))
                .dateliberation(new Date("05/02/2014"))
                .numbordereauanalytiqueinscrip("001")
                .numbordereauanalytiqueliberation("002")
                .build();
        privilegeEtHypothequeb = PrivilegeEtHypotheque.builder()
                .designationdudroitconstitue("Privilege")
                .titrefoncier(tfenreg)
                .dateinscription(new Date("01/02/2015"))
                .numbordereauanalytiqueinscrip("001")
                .build();

    }

    //Junit test pour tester la création d'une mutation pour un titre foncier existant
    @DisplayName("Junit test pour tester la création d'une hypotheque pour un TF donné")
    @Test
    public void givenHypotheque_whenHypothequecree_thenRetournerHypothequecree() {
        //given-precondition or setup mutation créé dans setup
        //when-action or the behaviour we are going test
        PrivilegeEtHypotheque SavedPrivilegeEtHypotheque=privilegeEtHypothequeRepository.save(privilegeEtHypotheque);
        //then-verify the output
        assertThat(SavedPrivilegeEtHypotheque).isNotNull();
    }
    @DisplayName("Junit test pour tester la récupération de plusieurs privileges et hypotheques pour un T.F. donné")
    @Test
    public void givenPlrsPrivetHypoavecTF_whenPrivetHypoExtraites_thenVerifierListPrivetHypoExtrait() {
        //given-precondition or setup mutation créé dans setup
        privilegeEtHypothequeRepository.save(privilegeEtHypotheque);
        privilegeEtHypothequeRepository.save(privilegeEtHypothequea);
        privilegeEtHypothequeRepository.save(privilegeEtHypothequeb);

        //when-action or the behaviour we are going test
        String numtitrefoncier=tfenreg.getNumtitrefoncier();
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<PrivilegeEtHypotheque> privilegeEtHypothequeList=privilegeEtHypothequeRepository.findBynumtitrefoncier(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(privilegeEtHypothequeList.size()).isEqualTo(3);

    }
    @Test
    public void givenNumTitreFoncier_whenHypothequeExtraites_thenVerifierListExtrait() {
        //given-precondition or setup mutation créé dans setup
        String choix="hypotheque";
        //when-action or the behaviour we are going test
        List<PrivilegeEtHypotheque> privilegeEtHypothequeList=privilegeEtHypothequeRepository.findBynumtitrefoncier(tfenreg.getNumtitrefoncier());






    }
}
