package br.com.cadocruz.easyvideos.uploader.application.video.create;

import br.com.cadocruz.easyvideos.uploader.domain.entities.Rating;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Resource;

import java.time.Year;

public record CreateVideoInput (
        String title,
        String description,
        Year launchedAt,
        double duration,
        Rating rating,
        Resource resource
) {
    public static CreateVideoInput from(final String title,
                                        final String description,
                                        final Year launchedAt,
                                        final double duration,
                                        final Rating rating,
                                        final Resource resource) {
        return new CreateVideoInput(title, description, launchedAt, duration, rating, resource);
    }
}
