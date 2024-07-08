package br.com.cadocruz.easyvideos.uploader.application;

public interface UseCase <INPUT, OUTPUT> {
    OUTPUT execute(INPUT input);
}
