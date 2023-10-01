package com.econs.titrefoncier.controller;

import com.econs.titrefoncier.model.TitreFoncier;
import com.econs.titrefoncier.service.impl.TitreFoncierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/titrefoncier")
public class TitreFoncierController {

    @Autowired
    private TitreFoncierServiceImpl titreFoncierService;

    @PostMapping()
    public ResponseEntity<TitreFoncier> EnregistrerTitreFoncier(@RequestBody TitreFoncier titreFoncier){
        return new ResponseEntity<TitreFoncier>(titreFoncierService.EnregistrerTitreFoncier(titreFoncier), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<TitreFoncier> mettreajourTitreFoncier(@RequestBody TitreFoncier titreFoncier){
        return new ResponseEntity<TitreFoncier>(titreFoncierService.EnregistrerTitreFoncier(titreFoncier), HttpStatus.OK);
    }

    @GetMapping({"volume"})
    public Optional<List<TitreFoncier>> rechercherTitreFonciersparvolume(@PathVariable("volume") String volume)
    {
        return titreFoncierService.rechercheparvolume(volume);
    }

    @GetMapping({"numtitrefoncier"})
    public  Optional<TitreFoncier>  rechercheruntitrefoncier(@PathVariable("numtitrefoncier") String numtitrefoncier)
    {
        return titreFoncierService.rechercheparnumerotitrefoncier(numtitrefoncier);

    }
    @DeleteMapping({"id"})
    public  ResponseEntity<String>  rechercheruntitrefoncier(@PathVariable("id") Integer id,TitreFoncier titreFoncier)
    {
        titreFoncierService.suppressionparidentifiant(id,titreFoncier);
        return new ResponseEntity<String>("Titre Foncier supprime avec succès!",HttpStatus.OK);

    }

}
