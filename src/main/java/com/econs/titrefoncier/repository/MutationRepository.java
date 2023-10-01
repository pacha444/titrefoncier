package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.Mutation;
import com.econs.titrefoncier.model.TitreFoncier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MutationRepository extends JpaRepository<Mutation,Integer> {


    @Query("select m from Mutation m join m.titrefoncier tf where tf.numtitrefoncier=:numtitrefoncier")
    List<Mutation> findBynumtitrefoncier(@Param("numtitrefoncier") String numtitrefoncier);
    @Query("select m from Mutation m where m.nomproprietaire=:nomproprietaire")
    List<Mutation> findBynomproprietaire(@Param("nomproprietaire") String nomproprietaire);

    @Query("select m from Mutation m join m.titrefoncier tf where tf.numtitrefoncier=:numtitrefoncier and m.nomproprietaire=:proprietaire")
    List<Mutation> findBynumtitrefoncieretproprietaire(@Param("numtitrefoncier") String numtitrefoncier,@Param("proprietaire") String proprietaire);
}
