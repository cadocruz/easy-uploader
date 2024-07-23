package br.com.cadocruz.easyvideos.uploader.infrasctructure.video;

import br.com.cadocruz.easyvideos.uploader.domain.entities.MediaResourceGateway;
import br.com.cadocruz.easyvideos.uploader.domain.entities.MediaStatus;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Resource;
import br.com.cadocruz.easyvideos.uploader.domain.entities.VideoMedia;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.services.StorageService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@ApplicationScoped
public class DefaultMediaResourceGateway implements MediaResourceGateway {
    private static final Logger log = LoggerFactory.getLogger(DefaultMediaResourceGateway.class);

    @Inject
    StorageService mStorageService;


    @Override
    public Uni<Void> storeAudioVideo(String anId, Resource aResource) {
        log.info("Storing audio video {}", anId);
        return  mStorageService.store(anId, aResource);
    }

    @Override
    public Optional<Resource> getResource(String anId) {
        return Optional.empty();
    }

    @Override
    public void clearResources(String anId) {

    }
}
