package br.com.cadocruz.easyvideos.uploader.domain.entities;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = false)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoMedia {
    private final String id;
    private final String checksum;
    private final String name;
    private final String rawLocation;
    private final String encodedLocation;
    private final MediaStatus status;

    public static VideoMedia from(String checksum, String name, String rawLocation, String encodedLocation, MediaStatus status) {
        return new VideoMedia(NanoIdUtils.randomNanoId(), checksum, name, rawLocation, encodedLocation, status);
    }

    public static VideoMedia from(final String id, final String checksum, final String name, final String rawLocation, final String encodedLocation, final MediaStatus status) {
        return new VideoMedia(id, checksum, name, rawLocation, encodedLocation, status);
    }

}
