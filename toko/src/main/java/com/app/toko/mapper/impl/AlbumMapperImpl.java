package com.app.toko.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.toko.entity.Album;
import com.app.toko.mapper.AlbumMapper;
import com.app.toko.payload.response.AlbumResponse;
import com.app.toko.repository.AlbumRepository;

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
