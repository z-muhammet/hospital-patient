
package com.Hm.hospital_patient.application.FileProcess;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchFile {

  private Path base = Paths.get("").toAbsolutePath().normalize();
  private Path dataBasePath = base.resolve("src/main/java/com/Hm/hospital_patient/DataBase");

  public SearchFile() {

  }

  public Path getDataBasePath() {
    return dataBasePath;
  }

  public List<File> getAllFiles() {
    try {
      return Files.walk(dataBasePath)
          .filter(Files::isRegularFile)
          .filter(path -> path.toString().toLowerCase(Locale.ENGLISH).endsWith(".txt"))
          .map(Path::toFile)
          .collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public File getFilesByName(String name) {
    try {
      List<File> files = Files.walk(dataBasePath)
          .filter(Files::isRegularFile)
          .filter(path -> path.toString().toLowerCase(Locale.ENGLISH).startsWith(name))
          .map(Path::toFile)
          .collect(Collectors.toList());
      return files.isEmpty() ? null : files.get(0);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
