package br.com.cadocruz.easyvideos.uploader.domain.entities;

import io.smallrye.mutiny.Uni;

import java.util.Optional;

public interface VideoGateway {
    Video create(Video aVideo);

    void deleteById(String anId);

    Optional<Uni<Video>> findById(String anId);

    Uni<Video> update(Video aVideo);
}
