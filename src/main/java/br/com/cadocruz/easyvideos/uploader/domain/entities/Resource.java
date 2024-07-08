package br.com.cadocruz.easyvideos.uploader.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = false)
@Getter
@AllArgsConstructor
public class Resource {
    private final byte[] content;
    private final String checksum;
    private final String contentType;
    private final String name;

    public static Resource with(final byte[] content, final String checksum, final String contentType, final String name) {
        return new Resource(content, checksum, contentType, name);
    }
}
