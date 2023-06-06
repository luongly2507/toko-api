package com.app.toko.config;

import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

  public String getLocation() {
    return Paths.get("")
        .toAbsolutePath()
        .toString() + "/toko/src/main/resources/static/img/upload";
  }

}
