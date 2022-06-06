package com.josue.kodeur.xtremanalyse.dtos;

import com.josue.kodeur.xtremanalyse.entities.Prefecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author JosueKodeur
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RegionDto implements Serializable {
    private  Long id;
    private  String nom;
    private  Long nombreHabitant;
}
