package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.reports.ReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF;

/**
 * @author GhostKodeur
 **/

@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
@CrossOrigin("*")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/accident/{condition}")
    public ResponseEntity<byte[]> generateReport(@PathVariable String condition) throws JRException, FileNotFoundException {
        byte[] data = reportService.exportAccidentByCondition(condition);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(APPLICATION_PDF)
                .body(data);
    }

    @GetMapping(path = "userList/")
    public ResponseEntity<Resource> userList() throws JRException, IOException {
        var filename = reportService.exportUserList();
        Path path = Path.of(filename);
        if (!Files.exists(path)){
            throw new FileNotFoundException("File not found"+path);
        }
        Resource resource = new UrlResource(path.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "users.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping(path = "evolutivite-par-mois/{annee}")
    public  HashMap<Integer, Integer> evolutiviteParMois(@RequestParam List<Integer> mois, @PathVariable("annee") Integer annee){
        return reportService.evolutiviteParMois(mois ,annee);
    }

    @GetMapping(path = "evolutivite-par-annee")
    public  HashMap<Integer, Integer> evolutiviteParAnnee(@RequestParam List<Integer> annee){
        return reportService.evolutiviteParAnnee(annee);
    }

    @GetMapping(path = "evolutivite-par-mort-annee")
    public HashMap<Integer, Integer> evolutiviteParMortAnnee(@RequestParam List<Integer> annee){
        return reportService.evolutiviteParMortAnnee(annee);
    }

    @GetMapping(path = "accidents-impliquant-blesse-par-annee")
    public HashMap<Integer, Integer> accidentImpliquantBlesse(@RequestParam List<Integer> annee){
        return reportService.accidentImpliquantBlesse(annee);
    }

    @GetMapping(path = "accidents-par-zone")
    public HashMap<String, Integer> accidentParZone(){
        return reportService.accidentParZone();
    }

    @GetMapping(path = "accidents-par-type-route/{id}")
    public HashMap<Integer, Integer> accidentParTypeRoute(@RequestParam List<Integer> annees, @PathVariable Long id){
        return reportService.evolutiviteParMortParTypeRoute(annees, id);
    }
}
