package br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.repository;

import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.VideoJpaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VideoRepository  implements PanacheRepository<VideoJpaEntity> {
}
