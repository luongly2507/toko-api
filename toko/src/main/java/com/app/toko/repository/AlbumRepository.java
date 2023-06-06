package com.app.toko.repository;

import com.app.toko.entity.Album;
import com.app.toko.entity.AlbumId;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, AlbumId> {

    Set<Album> findByBookId(UUID id);
}
