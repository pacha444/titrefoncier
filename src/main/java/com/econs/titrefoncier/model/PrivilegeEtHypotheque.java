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
@Table(name="privilegeethypotheque")
public class PrivilegeEtHypotheque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idprivethypotheque;

    private String numbordereauanalytiqueinscrip;

    private Date dateinscription;

    private String designationdudroitconstitue;

    private double montantcharge;

    private String numbordereauanalytiqueliberation;

    private Date dateliberation;
    @JoinColumn(name = "titrefoncier", referencedColumnName = "idtitrefoncier")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TitreFoncier titrefoncier;
}
