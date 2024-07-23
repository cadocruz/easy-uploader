package br.com.cadocruz.easyvideos.uploader.application.video.create;

import br.com.cadocruz.easyvideos.uploader.application.UseCase;
import br.com.cadocruz.easyvideos.uploader.domain.entities.*;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateVideoUseCase implements UseCase<CreateVideoInput, CreateVideoOutput> {

    @Inject
    VideoGateway videoGateway;
    @Inject
    MediaResourceGateway mediaResourceGateway;


    @Override
    @WithTransaction
    public Uni<CreateVideoOutput> execute(CreateVideoInput createVideoInput) {
        final var title = createVideoInput.title();
        final var description = createVideoInput.description();
        final var launchedAt = createVideoInput.launchedAt();
        final var duration = createVideoInput.duration();
        final var rating = createVideoInput.rating();
        final var resource = createVideoInput.resource();

        final var video = Video.with(title, description, launchedAt, duration, rating);

        final var videoMedia = VideoMedia.from(resource.checksum(), resource.name(),resource.name(), "", MediaStatus.PENDING);

        video.updateVideoMedia(videoMedia);
        video.validate();

        return videoGateway.create(video)
                .onItem().call(v -> mediaResourceGateway.storeAudioVideo(v.id(), resource))
                .onItem().transform(vm -> new CreateVideoOutput(video.id()))
                .onFailure().invoke(t -> System.out.println(t.getMessage()));
    }
}

