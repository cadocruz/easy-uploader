package br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence;

import br.com.cadocruz.easyvideos.uploader.domain.entities.MediaStatus;
import br.com.cadocruz.easyvideos.uploader.domain.entities.VideoMedia;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "VideoMedia")
@Table(name = "videos_video_media")
@Data
public class VideoMediaJpaEntity {

    @Id
    private String id;

    @Column(name = "checksum", nullable = false)
    private String checksum;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "encoded_path", nullable = false)
    private String encodedPath;

    @Column(name = "media_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaStatus status;

    public VideoMediaJpaEntity() {
    }

    private VideoMediaJpaEntity(
            final String id,
            final String checksum,
            final String name,
            final String filePath,
            final String encodedPath,
            final MediaStatus status
    ) {
        this.id = id;
        this.checksum = checksum;
        this.name = name;
        this.filePath = filePath;
        this.encodedPath = encodedPath;
        this.status = status;
    }

    public static VideoMediaJpaEntity from(final VideoMedia videoMedia) {
        return new VideoMediaJpaEntity(
                videoMedia.id(),
                videoMedia.checksum(),
                videoMedia.name(),
                videoMedia.rawLocation(),
                videoMedia.encodedLocation(),
                videoMedia.status()
        );
    }

    public static VideoMedia toDomain(VideoMediaJpaEntity videoMedia) {
        return VideoMedia.from(videoMedia.getId(),
                videoMedia.getChecksum(),
                videoMedia.getName(),
                videoMedia.getFilePath(),
                videoMedia.getEncodedPath(),
                videoMedia.getStatus());
    }
}
