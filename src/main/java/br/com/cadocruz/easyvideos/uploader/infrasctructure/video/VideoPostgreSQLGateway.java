package br.com.cadocruz.easyvideos.uploader.infrasctructure.video;

import br.com.cadocruz.easyvideos.uploader.domain.entities.Video;
import br.com.cadocruz.easyvideos.uploader.domain.entities.VideoGateway;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.VideoJpaEntity;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.repository.VideoRepository;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
@Blocking
public class VideoPostgreSQLGateway implements VideoGateway {

    @Inject
    VideoRepository videoRepository;


    @Override
    public Video create(Video aVideo) {
        this.videoRepository.persist(VideoJpaEntity.from(aVideo));
        return aVideo;
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
