package org.kenuki.landingserver.services;

import lombok.AllArgsConstructor;
import org.kenuki.landingserver.configurations.ImageStorageConfiguration;
import org.kenuki.landingserver.entities.Image;
import org.kenuki.landingserver.exceptions.BadImagePathException;
import org.kenuki.landingserver.exceptions.ImageNotFoundException;
import org.kenuki.landingserver.messages.DefaultMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ImageServices {
    private final Path rootPath;
    @Autowired
    public ImageServices(ImageStorageConfiguration imageStorageConfiguration){
        rootPath = Paths.get(imageStorageConfiguration.getAbsolute_path());
    }
    public Resource loadImage(String url) throws MalformedURLException, ImageNotFoundException {
        Path path = rootPath.resolve(url);
        Resource resource = new UrlResource(path.toUri());
        if(resource.exists() || resource.isReadable()){
            return resource;
        }else{
            throw new ImageNotFoundException(DefaultMessages.badImage);
        }

    }
    public Image storeImage(MultipartFile image) throws IOException, BadImagePathException {

        if (image.isEmpty()){
            throw new IOException();
        }
        Path path = rootPath.resolve(Paths.get(Objects.requireNonNull(image.getOriginalFilename()))).normalize().toAbsolutePath();
        if(path.getParent().equals(rootPath.toAbsolutePath())) {
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            Image imageEntity = new Image();
            imageEntity.setUrl(path.getFileName().toString());
            return imageEntity;
        }else {
            throw new BadImagePathException(DefaultMessages.badAccess);
        }
    }
    public void deleteImage(String url) throws IOException {
        Path path = rootPath.resolve(url).normalize();
        if (!Files.exists(path) || !path.startsWith(rootPath)){
            throw new IOException(DefaultMessages.imageNotFound);
        }
        Files.delete(path);
    }
}