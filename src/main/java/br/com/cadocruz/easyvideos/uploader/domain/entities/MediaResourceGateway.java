package br.com.cadocruz.easyvideos.uploader.domain.entities;


import io.smallrye.mutiny.Uni;

import java.util.Optional;

public interface MediaResourceGateway {

    Uni<Void>storeAudioVideo(String anId, Resource aResource);
    Optional<Resource> getResource(String anId);
    void clearResources(String anId);
}
