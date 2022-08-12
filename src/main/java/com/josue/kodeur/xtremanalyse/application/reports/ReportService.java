package com.josue.kodeur.xtremanalyse.application.reports;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author GhostKodeur
 **/

public interface ReportService {
    byte[] exportAccidentByCondition(String condition) throws FileNotFoundException, JRException;
    String exportUserList() throws IOException, JRException;

    HashMap<Integer, Integer> evolutiviteParAnnee(List<Integer> annee);
    HashMap<Integer, Integer> evolutiviteParMois(List<Integer> mois, Integer annee);
    HashMap<Integer, Integer> evolutiviteParMortAnnee(List<Integer> annee);
    HashMap<Integer, Integer> accidentImpliquantBlesse(List<Integer> annee);
    HashMap<String, Integer> accidentParZone();
    HashMap<Integer, Integer> evolutiviteParMortParTypeRoute(List<Integer> annees, Long id);

}
