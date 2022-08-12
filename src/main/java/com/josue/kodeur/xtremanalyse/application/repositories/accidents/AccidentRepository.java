package com.josue.kodeur.xtremanalyse.application.repositories.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JosueKodeur
 * **/

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {

    //Statistiques
    @Query(value = "select distinct a.zone from Accident a")
    List<String> allZone();

    @Query(value = "select count(a) from Accident a where year(a.dateAccident)=:annee")
    Integer evolutiviteParAnnee(@Param("annee") int annee);

    @Query(value = "select count(a) from Accident a where month(a.dateAccident)=:month and year(a.dateAccident)=:year")
    Integer evolutiviteParMois(@Param("month") int month, @Param("year") int year);

    @Query("select sum(a.nombreMorts) from Accident a where year(a.dateAccident) =:annee")
    Integer evolutiviteParMortParAnnee(@Param("annee") int annee);

    @Query("select sum(a.nombreMorts) from Accident a where a.typeRoute.id=:id and year(a.dateAccident) =:annee")
    Integer evolutiviteParMortParAnneeParTypeRoute(@Param("annee") int annee, @Param("id") Long id);

    @Query("select count(p.vehicule.accident) from PersonneImpliquee p" +
            " where p.gravite in ('BLESSE GRAVE', 'BLESSE LEGER') and year(p.vehicule.accident.dateAccident)=:annee")
    Integer accidentImpliquantBlesse(@Param("annee") int annee);

    Integer countAccidentByZone(String zone);

    List<Accident> findByConditionAtmospherique(String condition);
    List<Accident> findByTypeJour(String typeJour);
    List<Accident> findByEtatChaussee(String etatChaussee);
}