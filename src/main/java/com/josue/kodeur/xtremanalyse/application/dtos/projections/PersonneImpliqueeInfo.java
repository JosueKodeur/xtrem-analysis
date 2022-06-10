package com.josue.kodeur.xtremanalyse.application.dtos.projections;

import com.josue.kodeur.xtremanalyse.application.entities.personnes.Profession;

import java.time.LocalDateTime;

/**
 * @author JosueKodeur
 */

public interface PersonneImpliqueeInfo {
    Long getId();

    PietonInfo getPieton();

    ConducteurInfo getConducteur();

    PassagerInfo getPassager();

    interface PietonInfo {
        Long getId();

        String getNom();

        String getPrenom();

        int getAge();

        Character getSexe();

        String getNationalite();

        String getLesion();

        String getProtection();

        String getGravite();

        Profession getProfession();

        LocalDateTime getCreatedAt();

        LocalDateTime getUpdateAt();
    }

    interface ConducteurInfo {
        Long getId();

        String getNom();

        String getPrenom();

        int getAge();

        Character getSexe();

        String getNationalite();

        String getLesion();

        String getProtection();

        Boolean isAlcoolemie();

        Boolean isStupefiant();

        String getTypePermis();

        String getNumeroPermis();

        String getAnneePermis();

        String getPaysPermis();

        Profession getProfession();

        VehiculeInfo getVehicule();

        LocalDateTime getCreatedAt();

        LocalDateTime getUpdateAt();
    }

    interface PassagerInfo {
        Long getId();

        String getNom();

        String getPrenom();

        int getAge();

        Character getSexe();

        String getNationalite();

        String getLesion();

        String getProtection();

        String getGravite();

        Profession getProfession();

        LocalDateTime getCreatedAt();

        LocalDateTime getUpdateAt();
    }
}
