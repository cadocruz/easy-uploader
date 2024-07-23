package br.com.cadocruz.easyvideos.uploader.application;

import io.smallrye.mutiny.Uni;

public interface UseCase <INPUT, OUTPUT> {
    Uni<OUTPUT> execute(INPUT input);
}
