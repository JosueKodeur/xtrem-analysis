package com.josue.kodeur.xtremanalyse.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
