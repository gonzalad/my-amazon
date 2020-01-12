package com.example.myamazon.web;

import com.example.myamazon.entity.Identifiable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public final class ControllerUtils {

    private ControllerUtils() {
        // A utility classe must not be instanciated (hence this private constructor)
    }

    /**
     * Adds location header to point to the created or updated resource.
     *
     * @param resource created / updated resource
     * @return Location header to point to the created/updated resource.
     *
     */
    public static HttpHeaders getHeadersWithLocation(Identifiable resource) {

        return getHeadersWithLocation(resource.getId());
    }

    /**
     * Adds location header to point to the created or updated resource.
     *
     * @param id identifier of the created / updated resource
     * @return Location header to point to the created/updated resource.
     *
     */
    public static HttpHeaders getHeadersWithLocation(String id) {
        ServletUriComponentsBuilder builder = fromCurrentRequest();
        URI uri;
        if (builder.toUriString().contains(id)) {
            uri = builder.build().toUri();
        } else {
            uri = builder.path("/{id}").buildAndExpand(id).toUri();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return httpHeaders;
    }
}
