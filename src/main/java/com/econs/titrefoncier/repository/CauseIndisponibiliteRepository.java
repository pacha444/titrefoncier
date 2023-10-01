package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.CauseIndisponibilite;
import com.econs.titrefoncier.model.ModifConstImmeuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauseIndisponibiliteRepository extends JpaRepository<CauseIndisponibilite,Integer> {

    @Query("select c from CauseIndisponibilite c join c.titrefoncier tf where tf.numtitrefoncier=:numtitrefoncier")
    List<CauseIndisponibilite> findBynumtitrefoncier(String numtitrefoncier);
}
