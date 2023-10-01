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
@Table(name="modifconstimmeuble")
public class ModifConstImmeuble implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmodconstimm;
    private String typemodconstimm;
    private String numborderauanalytique;
    private Date dateinscription;
    private String numtitrefoncierorigine;
    private String designationimmeuble;
    private String modeacquisitionoualienation;
    private String contenanceimmeubleacquis;
    private Double prixacquisition;
    @JoinColumn(name = "titrefoncier", referencedColumnName = "idtitrefoncier")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TitreFoncier titrefoncier;

}
