package com.josue.kodeur.xtremanalyse.application.services.accidents;

import com.josue.kodeur.xtremanalyse.application.entities.accidents.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author JosueKodeur
 */

public interface ImageService {
    Image create(MultipartFile file) throws IOException;
}
