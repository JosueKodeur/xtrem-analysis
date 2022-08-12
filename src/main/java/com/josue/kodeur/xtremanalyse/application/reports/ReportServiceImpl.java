package com.josue.kodeur.xtremanalyse.application.reports;

import com.josue.kodeur.xtremanalyse.application.repositories.accidents.AccidentRepository;
import com.josue.kodeur.xtremanalyse.security.services.UserService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.BASE_FOLDER;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.SERVER_FOLDER;

/**
 * @author GhostKodeur
 **/

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final AccidentRepository accidentRepository;
    private UserService userService;

    @Override
    public byte[] exportAccidentByCondition(String condition) throws FileNotFoundException, JRException {
        var accidents = accidentRepository.findByConditionAtmospherique(condition);
        File file = ResourceUtils.getFile("classpath:reports_file/accident_stat.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(accidents);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Xtrem-analyse");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public String exportUserList() throws IOException, JRException {
        var users = userService.userList();
        Path reportFolder = Paths.get(System.getProperty(SERVER_FOLDER)+BASE_FOLDER+"/report").toAbsolutePath().normalize();
        if (!Files.exists(reportFolder)){
            Files.createDirectory(reportFolder);
        }
        File file = ResourceUtils.getFile("classpath:reports_file/user_list.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRSaver.saveObject(jasperReport, "usersList.jasper");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users, false);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Xtrem-analyse");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint,  reportFolder+"/users.pdf");

        return reportFolder+"/users.pdf";
    }

    @Override
    public HashMap<Integer, Integer> evolutiviteParAnnee(List<Integer> annee) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (var anne: annee) {
            var accidents = accidentRepository.evolutiviteParAnnee(anne);
            if (accidents==null)
                accidents=0;
            result.put(anne, accidents);
        }
        return result;
    }

    @Override
    public HashMap<Integer, Integer> evolutiviteParMois(List<Integer> mois, Integer annee) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (var _mois: mois) {
            var accidents = accidentRepository.evolutiviteParMois(_mois, annee);
            if (accidents==null)
                accidents=0;
            result.put(_mois, accidents);
        }
        return result;
    }

    @Override
    public HashMap<Integer, Integer> evolutiviteParMortAnnee(List<Integer> annee) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (var anne: annee) {
            var morts = accidentRepository.evolutiviteParMortParAnnee(anne);
            if (morts==null)
                morts=0;
            result.put(anne, morts);
        }
        return result;
    }

    @Override
    public HashMap<Integer, Integer> accidentImpliquantBlesse(List<Integer> annee) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (var anne: annee) {
            var accidents = accidentRepository.accidentImpliquantBlesse(anne);
            if (accidents==null)
                accidents=0;
            result.put(anne, accidents);
        }
        return result;
    }

    @Override
    public HashMap<String, Integer> accidentParZone() {

        var allZone = accidentRepository.allZone();
        HashMap<String, Integer> result = new HashMap<>();
        for (var zone: allZone) {
            var accidents = accidentRepository.countAccidentByZone(zone);
            if (accidents==null)
                accidents=0;
            result.put(zone, accidents);
        }
        return result;
    }

    @Override
    public HashMap<Integer, Integer> evolutiviteParMortParTypeRoute(List<Integer> annees, Long id) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (var annee : annees){
            var accidents = accidentRepository.evolutiviteParMortParAnneeParTypeRoute(annee, id);
            if (accidents==null)
                accidents=0;
            result.put(annee, accidents);
        }
        return result;
    }

}














