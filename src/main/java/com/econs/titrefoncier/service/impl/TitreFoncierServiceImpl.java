package com.econs.titrefoncier.service.impl;

import com.econs.titrefoncier.exception.RessourceNotFoundException;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.TitreFoncierRepository;
import com.econs.titrefoncier.service.TitreFoncierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitreFoncierServiceImpl implements TitreFoncierService {


    private final TitreFoncierRepository titreFoncierRepository;
    private final MutationServiceImpl mutationService;
    private final DrcdServiceImpl drcdService;
    private final CauseIndisponibiliteServiceImpl causeIndisponibiliteService;

    private final ModifConstImmeubleServiceImpl modifConstImmeubleService;

    private final PrivilegeEtHypothequeServiceImpl privilegeEtHypothequeService;


    public TitreFoncierServiceImpl(TitreFoncierRepository titreFoncierRepository, MutationServiceImpl mutationService, DrcdServiceImpl drcdService, CauseIndisponibiliteServiceImpl causeIndisponibiliteService, ModifConstImmeubleServiceImpl modifConstImmeubleService, PrivilegeEtHypothequeServiceImpl privilegeEtHypothequeService) {
        this.titreFoncierRepository = titreFoncierRepository;
        this.mutationService = mutationService;
        this.drcdService = drcdService;
        this.causeIndisponibiliteService = causeIndisponibiliteService;
        this.modifConstImmeubleService = modifConstImmeubleService;
        this.privilegeEtHypothequeService = privilegeEtHypothequeService;
    }

    /**
     * @param titreFoncier
     * @return
     */
    @Override
    public TitreFoncier EnregistrerTitreFoncier(TitreFoncier titreFoncier) {

        TitreFoncier tfcree = titreFoncierRepository.save(titreFoncier);
        tfcree.getMutationList().stream().forEach(
                mutation -> mutationService.enregistrermutation(mutation, tfcree)
        );
        tfcree.getDrcdList().stream().forEach(
                drcd -> drcdService.enregistrerdrcd(drcd, tfcree)
        );
        tfcree.getCauseindisponibiliteList().forEach(
                causeIndisponibilite -> causeIndisponibiliteService.enregistrercauseindisponibilite(causeIndisponibilite, tfcree)
        );
        tfcree.getModconstimmList().stream().forEach(
                modifConstImmeuble -> modifConstImmeubleService.enregistrerModificationConstitutionImmeuble(modifConstImmeuble, tfcree)
        );
        tfcree.getPrivethypothequesList().stream().forEach(
                privilegeEtHypotheque -> privilegeEtHypothequeService.enregistrerprivilegeethypotheque(privilegeEtHypotheque, tfcree)
        );
        return tfcree;
    }

    /**
     * @return
     */
    @Override
    public List<TitreFoncier> getAllTitreFoncier() {
        return titreFoncierRepository.findAll();
    }

    /**
     * @param numtitrefoncier
     * @return
     */
    @Override
    public Optional<TitreFoncier> rechercheparnumerotitrefoncier(String numtitrefoncier) {
        Optional<TitreFoncier> titreFoncierOptional = titreFoncierRepository.findBynumtitrefoncier(numtitrefoncier);
        if (!titreFoncierOptional.isPresent()) {
            throw new RessourceNotFoundException("Ce titre foncier n'existe pas dans la base de données");
        }

        return titreFoncierOptional;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public Optional<List<TitreFoncier>> rechercheparvolume(String s) {
        Optional<List<TitreFoncier>> titreFoncierList = titreFoncierRepository.findByVolume(s);

        if (titreFoncierList.isEmpty()) {
            throw new RessourceNotFoundException("Ce volume n'existe pas ou ne contient pas de titre");
        }

        return titreFoncierList;
    }

    /**
     * @param idtitrefoncier
     * @param titreFoncier
     */
    @Override
    public void suppressionparidentifiant(Integer idtitrefoncier, TitreFoncier titreFoncier)
    {

        if (!titreFoncier.getMutationList().isEmpty()) {
            titreFoncier.getMutationList().stream().forEach(mutation -> mutationService.delete(mutation));
        }
        if (!titreFoncier.getDrcdList().isEmpty()) {
            titreFoncier.getDrcdList().stream().forEach(drcd -> drcdService.delete(drcd));
        }
        if (!titreFoncier.getCauseindisponibiliteList().isEmpty()) {
            titreFoncier.getCauseindisponibiliteList().stream().forEach(causeIndisponibilite -> causeIndisponibiliteService.delete(causeIndisponibilite));
        }
        if (!titreFoncier.getPrivethypothequesList().isEmpty()) {
            titreFoncier.getPrivethypothequesList().stream().forEach(privilegeEtHypotheque -> privilegeEtHypothequeService.delete(privilegeEtHypotheque));
        }
        if (!titreFoncier.getModconstimmList().isEmpty()) {
            titreFoncier.getModconstimmList().stream().forEach(modifConstImmeuble -> modifConstImmeubleService.delete(modifConstImmeuble));
        }


        titreFoncierRepository.deleteById(idtitrefoncier);


    }
}
