package br.com.cadocruz.easyvideos.uploader.domain.entities;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Year;

@Accessors(fluent = true, chain = false)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Video {
    private String id;
    private String title;
    private String description;
    private Year launchedAt;
    private double duration;
    private Rating rating;
    private boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private VideoMedia videoMedia;

    private Video(final String title, final String description, final Year launchedAt, final double duration, final Rating rating, final VideoMedia videoMedia) {
        this.id = NanoIdUtils.randomNanoId();
        this.title = title;
        this.description = description;
        this.launchedAt = launchedAt;
        this.duration = duration;
        this.rating = rating;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.videoMedia = videoMedia;
    }

    public static Video with(final String title,
                             final String description,
                             final Year launchedAt,
                             final double duration,
                             final Rating rating,
                             final VideoMedia videoMedia) {
        return new Video(title, description, launchedAt, duration, rating, videoMedia);
    }

    public static Video with(final String title,
                             final String description,
                             final Year launchedAt,
                             final double duration,
                             final Rating rating) {
        return new Video(title, description, launchedAt, duration, rating, null);
    }

    public static Video with(final String id,
                             final String title,
                             final String description,
                             final Year launchedAt,
                             final double duration,
                             final Rating rating,
                             final boolean published,
                             final LocalDateTime createdAt,
                             final LocalDateTime updatedAt,
                             final VideoMedia videoMedia) {
        return new Video(id, title, description, launchedAt, duration, rating, published, createdAt, updatedAt, videoMedia);
    }

    public Video updateVideoMedia(final VideoMedia videoMedia) {
        this.videoMedia = videoMedia;
       this.updatedAt = LocalDateTime.now();
        return this;
    }

    public Video validate() {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (launchedAt == null) {
            throw new IllegalArgumentException("LaunchedAt cannot be null");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        if (rating == null) {
            throw new IllegalArgumentException("Rating cannot be null");
        }
        if (videoMedia == null) {
            throw new IllegalArgumentException("Video file cannot be null");
        }
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Year getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(Year launchedAt) {
        this.launchedAt = launchedAt;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public VideoMedia getVideoMedia() {
        return videoMedia;
    }

    public void setVideoMedia(VideoMedia videoMedia) {
        this.videoMedia = videoMedia;
    }
}
