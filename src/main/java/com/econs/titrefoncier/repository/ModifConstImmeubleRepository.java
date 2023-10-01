package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.ModifConstImmeuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModifConstImmeubleRepository extends JpaRepository<ModifConstImmeuble,Integer> {
    @Query("select mod from ModifConstImmeuble mod join mod.titrefoncier tf where tf.numtitrefoncier=:numtitrefoncier")
    List<ModifConstImmeuble> findBynumtitrefoncier(String numtitrefoncier);

}
