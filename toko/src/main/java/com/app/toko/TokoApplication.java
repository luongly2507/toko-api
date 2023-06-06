package com.app.toko;

import com.app.toko.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TokoApplication {
  public static void main(String[] args) {
    SpringApplication.run(TokoApplication.class, args);
  }
}
