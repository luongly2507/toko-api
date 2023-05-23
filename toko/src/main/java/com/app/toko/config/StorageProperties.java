package com.app.toko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.servlet.ServletContext;

@ConfigurationProperties("storage")
public class StorageProperties {
  @Autowired
  ServletContext context;

  public String getLocation() {
    return this.context.getRealPath("resources/img/upload");
  }

}
