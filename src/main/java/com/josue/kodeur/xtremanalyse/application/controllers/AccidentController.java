package com.josue.kodeur.xtremanalyse.application.controllers;

import com.josue.kodeur.xtremanalyse.application.dtos.projections.PersonneImpliqueeInfo;
import com.josue.kodeur.xtremanalyse.application.entities.accidents.Accident;
import com.josue.kodeur.xtremanalyse.application.exceptions.NotFoundException;
import com.josue.kodeur.xtremanalyse.application.services.accidents.AccidentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.BASE_FOLDER;
import static com.josue.kodeur.xtremanalyse.application.utils.Constants.SERVER_FOLDER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

/**
 * @author GhostKodeur
 */

@RestController
@RequestMapping("/api/v1/accident")
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    public static final String DIRECTORY = System.getProperty(SERVER_FOLDER)+BASE_FOLDER;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/add")
    public Accident add(@RequestPart("files") List<MultipartFile> files,
                        @RequestPart("accident") Accident accident) throws NotFoundException, IOException {
        return accidentService.save(accident, files);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/update/{id}")
    public Accident update(@RequestPart("files") List<MultipartFile> files, @RequestPart("accident") Accident accident, @PathVariable("id") Long id) throws NotFoundException, IOException {
        if (files == null)
            throw new RuntimeException("");
        return accidentService.update(id, accident, files);
    }

    @GetMapping("/{id}")
    public Accident details(@PathVariable("id") Long id) throws NotFoundException{
        return accidentService.details(id);
    }

    @GetMapping("/list-personnes-impliquee-dans-un-accident/{id}")
    public List<PersonneImpliqueeInfo> listPersonnesImpliqueeDansAccident(@PathVariable("id") Long id) throws NotFoundException{
        return accidentService.listPersonnesImpliqueeDansAccident(id);
    }

    @GetMapping("/")
    public List<Accident> listAll(){
        return accidentService.listAll();
    }

    @GetMapping(path = "/images/{filename}", produces = {IMAGE_JPEG_VALUE})
    public byte[] get(@PathVariable("filename")String filename) throws IOException{
        return Files.readAllBytes(Paths.get(DIRECTORY+filename));
    }

}
