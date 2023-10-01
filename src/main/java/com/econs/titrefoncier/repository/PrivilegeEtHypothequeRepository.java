package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.ModifConstImmeuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.econs.titrefoncier.model.PrivilegeEtHypotheque;

import java.util.List;

@Repository
public interface PrivilegeEtHypothequeRepository extends JpaRepository<PrivilegeEtHypotheque,Integer> {

    @Query("select ph from PrivilegeEtHypotheque  ph join ph.titrefoncier tf where tf.numtitrefoncier=:numtitrefoncier")
    List<PrivilegeEtHypotheque> findBynumtitrefoncier(String numtitrefoncier);
}
