package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.ModifConstImmeuble;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.ModifConstImmeubleRepository;
import com.econs.titrefoncier.repository.TitreFoncierRepository;
import com.econs.titrefoncier.service.impl.ModifConstImmeubleServiceImpl;
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
public class ModifConstImmeubleServiceTests {
    @Mock
    private ModifConstImmeubleRepository modifConstImmeubleRepository;
    @Mock
    private TitreFoncierRepository titreFoncierRepository;

    private TitreFoncier titreFoncier;

    private TitreFoncier tfenreg;

    private ModifConstImmeuble modifConstImmeuble;
    private ModifConstImmeuble modifConstImmeublea;
    private ModifConstImmeuble modifConstImmeubleb;
    @InjectMocks
    private ModifConstImmeubleServiceImpl modifConstImmeubleService;

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
        BDDMockito.given(modifConstImmeubleRepository.save(modifConstImmeuble)).willReturn(modifConstImmeuble);
        //when-action or the behaviour we are going test
        ModifConstImmeuble SavedModifConstImmeuble= modifConstImmeubleService.enregistrerModificationConstitutionImmeuble(modifConstImmeuble,tfenreg);
        //then-verify the output
        assertThat(SavedModifConstImmeuble).isNotNull();
    }

    @DisplayName("Junit test pour tester la récupération de plusieurs modifications pour un T.F. donné")
    @Test
    public void givenPlrsModificationsavecTF_whenModificationsExtraites_thenVerifierListModifications() {
        //given-precondition or setup mutation créé dans setup
        String numtitrefoncier="2345/DP";
        BDDMockito.given(modifConstImmeubleRepository.findBynumtitrefoncier(numtitrefoncier)).willReturn(List.of(modifConstImmeuble,modifConstImmeublea,modifConstImmeubleb));
        //when-action or the behaviour we are going test
        List<ModifConstImmeuble> modifConstImmeubleList=modifConstImmeubleService.recherchermodificationconstitutionimmeublepartf(numtitrefoncier);
        //then-verify the output
        assertThat(modifConstImmeubleList.size()).isEqualTo(3);

    }
    @DisplayName("Junit test pour recuperer les modifications pour un type donne")
    @Test
    public void givenNumTitreFoncier_whenModifExtraites_thenVerifieListExtrait() {
        //given-precondition or setup mutation créé dans setup
        String numtitrefoncier="2345/DP";
        String choix="augmentation";
        BDDMockito.given(modifConstImmeubleRepository.findBynumtitrefoncier(numtitrefoncier)).willReturn(List.of(modifConstImmeuble,modifConstImmeublea,modifConstImmeubleb));
        //when-action or the behaviour we are going test
        List<ModifConstImmeuble> modifConstImmeubleList=modifConstImmeubleService.recherchermodificationconstitutionimmeublepartfettypedonne(numtitrefoncier,choix);

        //then-verify the output
        assertThat(modifConstImmeubleList).isNotNull();

    }
}
