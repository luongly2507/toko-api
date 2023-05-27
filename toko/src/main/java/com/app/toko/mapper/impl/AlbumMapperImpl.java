package com.app.toko.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.app.toko.entity.Album;
import com.app.toko.mapper.AlbumMapper;
import com.app.toko.payload.response.AlbumResponse;

@Component
public class AlbumMapperImpl implements AlbumMapper {

    @Override
    public Set<AlbumResponse> toAlbumResponses(Set<Album> albums) {
        Set<AlbumResponse> albumResponses = new HashSet<AlbumResponse>();
        albums.forEach(
                album -> {
                    albumResponses.add(
                            AlbumResponse.builder()
                                    .imageSource(album.getId().getImageSource())
                                    .isPresentation(album.isPresentation())
                                    .build());
                });
        return albumResponses;
    }

}
