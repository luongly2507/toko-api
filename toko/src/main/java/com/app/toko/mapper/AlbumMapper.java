package com.app.toko.mapper;

import java.util.Set;

import com.app.toko.entity.Album;
import com.app.toko.payload.response.AlbumResponse;

public interface AlbumMapper {
    public Set<AlbumResponse> toAlbumResponses(Set<Album> albums);

}
