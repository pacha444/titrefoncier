package com.econs.titrefoncier.model;

import com.econs.titrefoncier.repository.PrivilegeEtHypothequeRepository;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="titrefoncier")
public class TitreFoncier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtitrefoncier;
    private String numtitrefoncier;
    private String volume;
    private String natetconsistanceimmeuble;
    private String titrefoncierorigine;
    private String contenance;
    private String situation;
    private String numlot;
    private boolean titreradie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titrefoncier", fetch = FetchType.EAGER)
    private List<Drcd> drcdList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titrefoncier", fetch = FetchType.EAGER)
    private List<ModifConstImmeuble> modconstimmList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titrefoncier", fetch = FetchType.EAGER)
    private List<CauseIndisponibilite> causeindisponibiliteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titrefoncier", fetch = FetchType.EAGER)
    private List<PrivilegeEtHypotheque> privethypothequesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titrefoncier", fetch = FetchType.EAGER)
    private List<Mutation> mutationList;

}
