package com.app.toko.repository;

import com.app.toko.entity.Publisher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {}
