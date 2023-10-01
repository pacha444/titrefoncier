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
@Table(name="drcd")
public class Drcd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddroitreelconstdem;
    private String numbordanalytiqueinscr;
    private Date dateinscription;
    private String indchargeoudroitconst;
    private Integer prixdedroitscedes;
    private String numbordanalytiquerad;
    private Date dateradiation;
    @JoinColumn(name = "titrefoncier", referencedColumnName = "idtitrefoncier")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TitreFoncier titrefoncier;
}
