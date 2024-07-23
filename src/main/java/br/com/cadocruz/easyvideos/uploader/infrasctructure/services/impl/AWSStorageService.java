package br.com.cadocruz.easyvideos.uploader.infrasctructure.services.impl;

import br.com.cadocruz.easyvideos.uploader.domain.entities.Resource;
import br.com.cadocruz.easyvideos.uploader.infrasctructure.services.StorageService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.naming.ServiceUnavailableException;
import java.util.Map;

@ApplicationScoped
public class AWSStorageService implements StorageService {
    public static final String NAME = "name";
    private static final Logger log = LoggerFactory.getLogger(AWSStorageService.class);

    @ConfigProperty(name = "bucket.name")
    String bucketName;

    @Inject
    S3AsyncClient s3Client;

    @Override
    public Uni<Void> store(String id, Resource resource) {

        final var request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(id)
                .metadata(Map.of(NAME, resource.name()))
                .contentType(resource.contentType())
                .build();


        return Uni.createFrom().item(
                    s3Client.putObject(request, AsyncRequestBody.fromBytes(resource.content())))
                .onItem().ignore().andSwitchTo(Uni.createFrom().voidItem())
                .onFailure().invoke(failure ->
                        System.out.println(failure.getMessage()));
    }
}
