package br.com.cadocruz;

import br.com.cadocruz.easyvideos.uploader.application.video.create.CreateVideoInput;
import br.com.cadocruz.easyvideos.uploader.application.video.create.CreateVideoUseCase;
import br.com.cadocruz.easyvideos.uploader.domain.entities.*;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CreateVideoUseCaseTest {

    @InjectMocks
    CreateVideoUseCase createVideoUseCase;
    @Mock
    VideoGateway videoGateway;
    @Mock
    MediaResourceGateway mediaResourceGateway;

    @Test
    void givenAnValidVideo_whenUploadVideo_thenReturnTrue() {
        final String title = "Titulo do video";
        final String description = "Descricao do video";
        final Year launchedAt = Year.of(2018);
        final double duration = 150d;
        final Rating rating = Rating.L;
        final Resource resource = Resource.with("check".getBytes(), "name", "localtion", "enclocation");
        var videoMedia = VideoMedia.from("123", "checksum", "name", "localtion", "enclocation", MediaStatus.PENDING);
        Mockito.when(mediaResourceGateway.storeAudioVideo(any(), eq(resource))).thenReturn(videoMedia);
        final var video = Video.with(title, description, launchedAt, duration, rating);
        Mockito.when(videoGateway.create(any())).thenReturn(video);

        final var input = CreateVideoInput.from(title, description, launchedAt, duration, rating, resource);
        final var output = createVideoUseCase.execute(input);

        Assertions.assertNotNull(output);
    }
}
