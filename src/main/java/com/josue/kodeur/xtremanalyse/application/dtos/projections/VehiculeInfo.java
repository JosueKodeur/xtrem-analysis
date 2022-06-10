package com.josue.kodeur.xtremanalyse.application.dtos.projections;

import java.time.LocalDateTime;

/**
 * @author JosueKodeur
 */

public interface VehiculeInfo {
    Long getId();

    String getImmatriculation();

    String getPaysImmatriculation();

    String getGenre();

    Boolean isIsPremierChoc();

    String getDebutCirculation();

    Boolean isIsControleTechValid();

    Boolean isIsAssuranceValid();

    String getSocieteAssurance();

    String getEtatGeneral();

    String getEtatPneus();

    String getEtatFeux();

    String getTypeChargement();

    String getVolumeChargement();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdateAt();
}
