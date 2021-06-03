package com.ssafy.test.model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.ssafy.test.controller.FilesController;
import com.ssafy.test.model.FileInfo;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      File d = new File(System.getProperty("user.dir") + "/uploads");
      System.out.println(d);
      if (d.isDirectory()) {
    	  System.out.println("폴더가 이미 있습니다.");
      } else {
    	  System.out.println("폴더를 생성합니다.");
    	  Files.createDirectory(root);
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
      System.out.println(this.root.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
//  public Stream<Path> load(String filename) {
//	  System.out.println("load");
//	  try {
//		  return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//	  } catch (IOException e) {
//		  throw new RuntimeException("Could not load the files!");
//	  }
//  }
  
  public Resource load(String filename) {
//	  System.out.println("load");
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
//	  System.out.println("deleteAll");
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
//	  System.out.println("loadAll");
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }
}