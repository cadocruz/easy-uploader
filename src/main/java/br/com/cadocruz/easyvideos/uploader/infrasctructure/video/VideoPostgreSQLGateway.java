package br.com.cadocruz.easyvideos.uploader.infrasctructure.video;

import br.com.cadocruz.ExampleResource;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Video;
import br.com.cadocruz.easyvideos.uploader.domain.entities.VideoGateway;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.VideoJpaEntity;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.repository.VideoRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.Optional;

@ApplicationScoped
public class VideoPostgreSQLGateway implements VideoGateway {

    private static final Logger log = Logger.getLogger(VideoPostgreSQLGateway.class);
    @Inject
    VideoRepository videoRepository;


    @Override
    @WithTransaction
    public Uni<Video> create(Video aVideo) {
        VideoJpaEntity videoJpaEntity = VideoJpaEntity.from(aVideo);
        return this.videoRepository.persistAndFlush(videoJpaEntity)
                .onFailure().invoke(t -> log.info(t.getMessage(), t))
                .onItem().transform(VideoJpaEntity::toAggregate);

//        return this.videoRepository.persistAndFlush(videoJpaEntity).onItem().transform(VideoJpaEntity::toAggregate)
//                .onFailure().invoke(t -> log.info(t.getMessage(), t));
    }

    @Override
    public void deleteById(String anId) {

    }

    @Override
    public Optional<Uni<Video>> findById(String anId) {
        return Optional.empty();
    }

    @Override
    public Uni<Video> update(Video aVideo) {
        return null;
    }
}
