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
@Table(name = "mutation")
public class Mutation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmutation")
    private Integer idmutation;
    private String idborderauana;
    private Date dateacquisition;
    private String modeacquisition;
    private int prixacquisition;
    private String nomproprietaire;
    private String lieunaiss;
    private Date datedenaissance;
    private String profession;
    private String actuel;
    @JoinColumn(name = "titrefoncier", referencedColumnName = "idtitrefoncier")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TitreFoncier titrefoncier;
}
