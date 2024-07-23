package br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.repository;

import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.VideoJpaEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;


public interface VideoRepository extends PanacheRepositoryBase<VideoJpaEntity, String> {
}
