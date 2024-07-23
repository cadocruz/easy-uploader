package br.com.cadocruz.easyvideos.uploader.infrasctructure.api;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/videos")
public interface VideoAPI {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Uni<Response> createVideo(
            @RestForm("title") String title,
            @RestForm("description") String description,
            @RestForm("year_launched") Integer yearLaunched,
            @RestForm("duration") Double duration,
            @RestForm("opened") Boolean opened,
            @RestForm("published") Boolean published,
            @RestForm("rating") String rating,
            @RestForm("video_file") FileUpload videoFile
    );

}
