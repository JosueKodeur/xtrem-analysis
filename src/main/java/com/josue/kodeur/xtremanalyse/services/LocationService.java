package com.josue.kodeur.xtremanalyse.services;

import java.util.List;

/**
 * @author JosueKodeur
 */

public interface LocationService<L> {
    L add(L l);
    void delete(Long id);
    L update(L l);
    List<L> listAll();
}
