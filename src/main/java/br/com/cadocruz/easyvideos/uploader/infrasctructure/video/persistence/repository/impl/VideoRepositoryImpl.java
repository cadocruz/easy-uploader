package br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.repository.impl;

import br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence.repository.VideoRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@WithSession
public class VideoRepositoryImpl implements VideoRepository {
}
