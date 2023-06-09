package com.app.toko.service.impl;

import com.app.toko.config.StorageProperties;
import com.app.toko.exception.ResourceNotFoundException;
import com.app.toko.exception.StorageException;
import com.app.toko.service.StorageService;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

	private final Path rootLocation;

	public StorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public String store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			// Create custom filename
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			// Get extension
			String ext = fileName.substring(
					fileName.lastIndexOf("."),
					fileName.length());
			fileName = fileName.substring(0, fileName.length() - ext.length());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
					"yyyy-MM-dd HH-mm-ss");
			String formatDateTime = LocalDateTime.now().format(formatter);
			// remove spaces and make lowercase
			fileName = fileName.concat(formatDateTime).concat(ext);
			fileName = fileName.toLowerCase().replaceAll(" ", "-");
			System.out.println(fileName);
			this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
					.normalize()
					.toAbsolutePath();
			if (fileName.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				System.out.println(this.rootLocation.resolve(fileName));

				Files.copy(
						inputStream,
						this.rootLocation.resolve(fileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
			return fileName;
		} catch (IOException e) {
			throw new StorageException("Failed to store file: " + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files
					.walk(this.rootLocation, 1)
					.filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ResourceNotFoundException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new ResourceNotFoundException("Could not read file: " + filename);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
