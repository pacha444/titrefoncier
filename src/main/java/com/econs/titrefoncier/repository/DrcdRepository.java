package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.Drcd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrcdRepository extends JpaRepository<Drcd,Integer> {

    @Query("select d from Drcd d join d.titrefoncier tf where tf.numtitrefoncier=:numtitrefoncier")
    List<Drcd> findBynumtitrefoncier(String numtitrefoncier);
    @Query("select d from Drcd d join d.titrefoncier tf where tf.numtitrefoncier=:titrefoncier and d.iddroitreelconstdem=:num")
    List<Drcd> findBynumtitrefoncieretnumdrcd(String titrefoncier, Integer num);
}
