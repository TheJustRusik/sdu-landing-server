package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.configurations.ImageStorageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class ImageServices {
    private final Path path;
    @Autowired
    public ImageServices(ImageStorageConfiguration imageStorageConfiguration){
        path = Paths.get(imageStorageConfiguration.getAbsolute_path());
    }
    public void loadImage(String url){
        try {
            Path path = this.path.resolve(url);
        }
    }
}