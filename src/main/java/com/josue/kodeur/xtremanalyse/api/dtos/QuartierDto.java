package com.josue.kodeur.xtremanalyse.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author JosueKodeur
 */

@Getter
@Setter
public class QuartierDto implements Serializable {
    private Long id;
    private String name;
}
