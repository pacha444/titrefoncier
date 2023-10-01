package com.econs.titrefoncier.service.impl;

import com.econs.titrefoncier.model.PrivilegeEtHypotheque;
import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.repository.PrivilegeEtHypothequeRepository;
import com.econs.titrefoncier.service.PrivilegeEtHypothequeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeEtHypothequeServiceImpl implements PrivilegeEtHypothequeService {

    private PrivilegeEtHypothequeRepository privilegeEtHypothequeRepository;

    public PrivilegeEtHypothequeServiceImpl(PrivilegeEtHypothequeRepository privilegeEtHypothequeRepository) {
        this.privilegeEtHypothequeRepository = privilegeEtHypothequeRepository;
    }

    /**
     * @param privilegeEtHypotheque
     * @param titreFoncier
     * @return
     */
    @Override
    public PrivilegeEtHypotheque enregistrerprivilegeethypotheque(PrivilegeEtHypotheque privilegeEtHypotheque, TitreFoncier titreFoncier) {
       privilegeEtHypotheque.setTitrefoncier(titreFoncier);
        return privilegeEtHypothequeRepository.save(privilegeEtHypotheque);
    }

    /**
     * @param numtitrefoncier
     * @param choix
     * @return
     */
    @Override
    public List<PrivilegeEtHypotheque> rechercherprivilegeouhypothequepartf(String numtitrefoncier,String choix) {
        return privilegeEtHypothequeRepository.findBynumtitrefoncier(numtitrefoncier).stream()
                .filter(c->c.getDesignationdudroitconstitue().equals(choix))
                .toList();
    }

    /**
     * @param numtitrefoncier
     * @return
     */
    @Override
    public List<PrivilegeEtHypotheque> rechercherprivilegeethypothequepartf(String numtitrefoncier) {
        return privilegeEtHypothequeRepository.findBynumtitrefoncier(numtitrefoncier);

    }

    /**
     * @param privilegeEtHypotheque
     */
    @Override
    public void delete(PrivilegeEtHypotheque privilegeEtHypotheque) {
        privilegeEtHypothequeRepository.delete(privilegeEtHypotheque);

    }
}
