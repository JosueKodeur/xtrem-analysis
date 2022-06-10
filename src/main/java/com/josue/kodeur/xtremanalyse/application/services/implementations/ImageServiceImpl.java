package com.josue.kodeur.xtremanalyse.application.services.implementations;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Image;
import com.josue.kodeur.xtremanalyse.application.services.accidents.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.josue.kodeur.xtremanalyse.application.utils.Constants.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author JosueKodeur
 */

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Override
    public Image create(MultipartFile file) throws IOException {
        Image image = new Image();
        if (file!=null){
            Path imageFolder = Paths.get(System.getProperty(SERVER_FOLDER)+BASE_FOLDER).toAbsolutePath().normalize();
            if (!Files.exists(imageFolder)){
                Files.createDirectory(imageFolder);
            }
            Files.deleteIfExists(Paths.get(imageFolder+ file.getOriginalFilename()+ APPLICATION_NAME +"."+IMAGE_EXTENSION));
            Files.copy(file.getInputStream(), imageFolder.resolve(file.getOriginalFilename()+APPLICATION_NAME+"."+IMAGE_EXTENSION), REPLACE_EXISTING);
            image.setNom(file.getOriginalFilename());
            image.setUrl(setImageUrl(file.getOriginalFilename()));
        }

        return image;
    }

    private String setImageUrl(String name) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/"+ACCIDENT_IMAGE_FOLDER+name+"_"+APPLICATION_NAME+"."+IMAGE_EXTENSION)
                .toUriString();
    }
}
