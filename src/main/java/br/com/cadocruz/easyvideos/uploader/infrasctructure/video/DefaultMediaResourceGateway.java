package br.com.cadocruz.easyvideos.uploader.infrasctructure.video;

import br.com.cadocruz.easyvideos.uploader.domain.entities.MediaResourceGateway;
import br.com.cadocruz.easyvideos.uploader.domain.entities.MediaStatus;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Resource;
import br.com.cadocruz.easyvideos.uploader.domain.entities.VideoMedia;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@ApplicationScoped
public class DefaultMediaResourceGateway implements MediaResourceGateway {
    private static final Logger log = LoggerFactory.getLogger(DefaultMediaResourceGateway.class);

    @Override
    public VideoMedia storeAudioVideo(String anId, Resource aResource) {
        log.info("Storing audio video {}", anId);
        return VideoMedia.from(anId, aResource.checksum(), aResource.name(),aResource.name(), "", MediaStatus.PENDING);
    }

    @Override
    public Optional<Resource> getResource(String anId) {
        return Optional.empty();
    }

    @Override
    public void clearResources(String anId) {

    }
}
