package br.com.cadocruz.easyvideos.uploader.infrasctructure.video.persistence;

import br.com.cadocruz.easyvideos.uploader.domain.entities.Rating;
import br.com.cadocruz.easyvideos.uploader.domain.entities.Video;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Year;

@Entity(name = "Video")
@Table (name = "videos")
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoJpaEntity extends PanacheEntityBase {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "year_launched", nullable = false)
    private int yearLaunched;

    @Column(name = "published", nullable = false)
    private boolean published;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;

    @Column(name = "duration", precision = 2)
    private double duration;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "video_id")
    private VideoMediaJpaEntity video;

    public VideoJpaEntity() {}

    public static VideoJpaEntity from(Video aVideo) {
        return new VideoJpaEntity(aVideo.id(),
                aVideo.title(),
                aVideo.description(),
                aVideo.launchedAt().getValue(),
                aVideo.published(),
                aVideo.rating(),
                aVideo.duration(),
                aVideo.createdAt(),
                null,
                VideoMediaJpaEntity.from(aVideo.videoMedia()));
    }

    public Video toAggregate() {
        return Video.with(getId(),
                getTitle(),
                getDescription(),
                Year.of(getYearLaunched()),
                getDuration(),
                getRating(),
                isPublished(),
                getCreatedAt(),
                getUpdatedAt(),
                VideoMediaJpaEntity.toDomain(getVideo())
        );
    }
}
