package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.ModifConstImmeuble;
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
public class ModifConstImmeubleRepositoryTests {

    @Autowired
    private ModifConstImmeubleRepository modifConstImmeubleRepository;


    @Autowired
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;

    private ModifConstImmeuble modifConstImmeuble;
    private ModifConstImmeuble modifConstImmeublea;
    private ModifConstImmeuble modifConstImmeubleb;

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
        modifConstImmeuble = ModifConstImmeuble.builder()
                .contenanceimmeubleacquis("150 m²")
                .typemodconstimm("augmentation")
                .numtitrefoncierorigine("2000/DP")
                .prixacquisition(15000000.00)
                .dateinscription(new Date("01/02/1999"))
                .modeacquisitionoualienation("distraction")
                .titrefoncier(titreFoncier)
                .numborderauanalytique("011")
                .designationimmeuble("Terrain Bati")
                .build();
        modifConstImmeublea = ModifConstImmeuble.builder()
                .contenanceimmeubleacquis("150 m²")
                .typemodconstimm("augmentation")
                .numtitrefoncierorigine("2000/DP")
                .prixacquisition(15000000.00)
                .dateinscription(new Date("01/02/1999"))
                .modeacquisitionoualienation("distraction")
                .titrefoncier(titreFoncier)
                .numborderauanalytique("011")
                .designationimmeuble("Terrain Bati")
                .build();
        modifConstImmeubleb = ModifConstImmeuble.builder()
                .contenanceimmeubleacquis("150 m²")
                .typemodconstimm("diminution")
                .numtitrefoncierorigine("2000/DP")
                .prixacquisition(15000000.00)
                .dateinscription(new Date("01/02/1999"))
                .modeacquisitionoualienation("distraction")
                .titrefoncier(titreFoncier)
                .numborderauanalytique("011")
                .designationimmeuble("Terrain Bati")
                .build();

    }

    //Junit test pour tester la création d'une mutation pour un titre foncier existant
    @DisplayName("Junit test pour tester la création d'une modification dans la constitution de l'immeuble pour un TF donné")
    @Test
    public void givenMCI_whenMCIcree_thenRetournerMCIcreee() {
        //given-precondition or setup mutation créé dans setup
        //when-action or the behaviour we are going test
       ModifConstImmeuble SavedModifConstImmeuble= modifConstImmeubleRepository.save(modifConstImmeuble);
        //then-verify the output
        assertThat(SavedModifConstImmeuble).isNotNull();
    }

    @DisplayName("Junit test pour tester la récupération de plusieurs modifications pour un T.F. donné")
    @Test
    public void givenPlrsModificationsavecTF_whenModificationsExtraites_thenVerifierListModifications() {
        //given-precondition or setup mutation créé dans setup
        modifConstImmeubleRepository.save(modifConstImmeuble);
        modifConstImmeubleRepository.save(modifConstImmeublea);
        modifConstImmeubleRepository.save(modifConstImmeubleb);

        //when-action or the behaviour we are going test
        String numtitrefoncier=tfenreg.getNumtitrefoncier();
        // List<Mutation> mutationList=mutationRepository.findByNumTitreFoncier(idtitrefoncier,numtitrefoncier);
        List<ModifConstImmeuble> modifConstImmeubleList=modifConstImmeubleRepository.findBynumtitrefoncier(numtitrefoncier);
        //then-verify the output
        assertThat(numtitrefoncier).isNotNull();
        assertThat(modifConstImmeubleList.size()).isEqualTo(3);

    }

}
