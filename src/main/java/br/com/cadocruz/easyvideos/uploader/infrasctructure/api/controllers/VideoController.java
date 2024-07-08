package br.com.cadocruz.easyvideos.uploader.infrasctructure.api.controllers;

import br.com.cadocruz.easyvideos.uploader.application.video.create.CreateVideoInput;
import br.com.cadocruz.easyvideos.uploader.application.video.create.CreateVideoUseCase;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Rating;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Resource;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.api.VideoAPI;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import static java.nio.file.Files.newInputStream;

public class VideoController implements VideoAPI {

    @Inject
    protected CreateVideoUseCase createVideoUseCase;

    @Override
    public String createVideo(String title,
                                     String description,
                                     Integer yearLaunched,
                                     Double duration,
                                     Boolean opened,
                                     Boolean published,
                                     String rating,
                                     FileUpload videoFile) {
        final var createVideoInput = CreateVideoInput.from(
                title,
                description,
                Year.of(yearLaunched),
                duration,
                Rating.valueOf(rating),
                resourceOf(videoFile)
        );
        return createVideoUseCase.execute(createVideoInput).id();
    }

    private Resource resourceOf(FileUpload videoFile) {
        if (videoFile == null) {
            return null;
        }
        InputStream targetStream = null;
        try {
            Path path = videoFile.uploadedFile();
            targetStream = newInputStream(path);
            byte[] content = targetStream.readAllBytes();
            return Resource.with(
                    content,
                    getCRC32Checksum(content),
                    videoFile.contentType(),
                    videoFile.fileName()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCRC32Checksum(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return String.valueOf(crc32.getValue());
    }
}
