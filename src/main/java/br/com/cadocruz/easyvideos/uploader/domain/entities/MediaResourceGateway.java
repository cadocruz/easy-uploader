package br.com.cadocruz.easyvideos.uploader.domain.entities;


import java.util.Optional;

public interface MediaResourceGateway {

    VideoMedia storeAudioVideo(String anId, Resource aResource);
    Optional<Resource> getResource(String anId);
    void clearResources(String anId);
}
