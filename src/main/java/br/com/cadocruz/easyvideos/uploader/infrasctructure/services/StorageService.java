package br.com.cadocruz.easyvideos.uploader.infrasctructure.services;

import br.com.cadocruz.easyvideos.uploader.domain.entities.Resource;
import io.smallrye.mutiny.Uni;

public interface StorageService {
    Uni<Void> store(String id, Resource resource);
}
