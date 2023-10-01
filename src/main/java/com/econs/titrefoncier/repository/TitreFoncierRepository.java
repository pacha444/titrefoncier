package com.econs.titrefoncier.repository;

import com.econs.titrefoncier.model.TitreFoncier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitreFoncierRepository extends JpaRepository<TitreFoncier,Integer> {

    Optional<List<TitreFoncier>> findByVolume(String volume);
    Optional<TitreFoncier> findBynumtitrefoncier(String numtitrefoncier);

}
