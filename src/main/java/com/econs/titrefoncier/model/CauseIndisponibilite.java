package com.econs.titrefoncier.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="causeindisponibilite")
public class CauseIndisponibilite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcausesindisponibilite")
    private Integer idcausesindisponibilite;
    private String numborderauanalytiqueins;
    private Date dateinscription;
    private String indclausesconv;
    private String numborderauanalytiquelib;
    private Date dateliberation;
    @JoinColumn(name = "titrefoncier", referencedColumnName = "idtitrefoncier")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TitreFoncier titrefoncier;

}
