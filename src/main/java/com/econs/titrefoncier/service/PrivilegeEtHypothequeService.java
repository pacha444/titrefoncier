package com.econs.titrefoncier.service;

import com.econs.titrefoncier.model.PrivilegeEtHypotheque;
import com.econs.titrefoncier.model.TitreFoncier;

import java.util.List;

public interface PrivilegeEtHypothequeService {


    PrivilegeEtHypotheque enregistrerprivilegeethypotheque(PrivilegeEtHypotheque privilegeEtHypotheque, TitreFoncier titreFoncier);

    List<PrivilegeEtHypotheque> rechercherprivilegeouhypothequepartf(String numtitrefoncier, String choix);

    List<PrivilegeEtHypotheque> rechercherprivilegeethypothequepartf(String numtitrefoncier);

    void delete(PrivilegeEtHypotheque privilegeEtHypotheque);
}
