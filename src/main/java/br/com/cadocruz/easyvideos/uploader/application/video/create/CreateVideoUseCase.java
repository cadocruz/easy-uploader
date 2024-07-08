package br.com.cadocruz.easyvideos.uploader.application.video.create;

import br.com.cadocruz.easyvideos.uploader.application.UseCase;
import br.com.cadocruz.easyvideos.uploader.domain.entities.MediaResourceGateway;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Video;
import br.com.cadocruz.easyvideos.uploader.domain.entities.VideoGateway;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateVideoUseCase implements UseCase<CreateVideoInput, CreateVideoOutput> {

    @Inject
    VideoGateway videoGateway;
    @Inject
    MediaResourceGateway mediaResourceGateway;


    @Override
    @Transactional
    public CreateVideoOutput execute(CreateVideoInput createVideoInput) {
        final var title = createVideoInput.title();
        final var description = createVideoInput.description();
        final var launchedAt = createVideoInput.launchedAt();
        final var duration = createVideoInput.duration();
        final var rating = createVideoInput.rating();
        final var resource = createVideoInput.resource();

        final var video = Video.with(title, description, launchedAt, duration, rating);

        var videoMedia = mediaResourceGateway.storeAudioVideo(video.id(), resource);
        video.updateVideoMedia(videoMedia);
        video.validate();
        videoGateway.create(video);
        return new CreateVideoOutput(video.id());
    }
}

