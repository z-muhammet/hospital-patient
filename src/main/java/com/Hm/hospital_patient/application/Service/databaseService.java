package com.Hm.hospital_patient.application.Service;

import org.springframework.stereotype.Service;

import com.Hm.hospital_patient.Model.Singleton;
import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.Model.UserDatabase;
import com.Hm.hospital_patient.application.FileProcess.ReadFile;
import com.Hm.hospital_patient.application.FileProcess.SearchFile;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class databaseService {
  SearchFile searchFile = new SearchFile();
  ReadFile ReadFile = new ReadFile();

  Map<String, User> globalUserMap = Singleton.getGlobalUserMap();
  UserDatabase db = new UserDatabase();

  public databaseService() {
    List<File> allFiles = searchFile.getAllFiles();
    db = ReadFile.readAllFile(allFiles);
    globalUserMap = db.getUsers();
  }

  public Map<String, User> getMap() {
    return globalUserMap;
  }

  public User get(String key) {
    return globalUserMap.get(key);
  }

  public void put(String key, User value) {
    globalUserMap.put(key, value);

    File file = searchFile.getFilesByName(key);
    if (file != null && file.exists()) {
      file.delete();
    }

    File newFile = new File(searchFile.getDataBasePath().toFile(), key + ".txt");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
      writer.write(value.getName() + "\n");
      writer.write(value.getSurname() + "\n");
      writer.write(value.getAge() + "\n");
      writer.write(value.getPhone() + "\n");
      writer.write(value.getEmail() + "\n");
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Dosya yazma hatası: " + newFile.getAbsolutePath());
    }
  }

  public void remove(String key) {
    globalUserMap.remove(key);
    File fileToDelete = searchFile.getFilesByName(key);
    if (fileToDelete != null && fileToDelete.exists()) {
      try {
        if (!fileToDelete.delete()) {
          System.err.println("Failed to delete file: " + fileToDelete.getAbsolutePath());
        }
      } catch (SecurityException e) {
        System.err.println("Permission denied to delete file: " + fileToDelete.getAbsolutePath());
      }
    }
  }

  public boolean containsKey(String key) {
    return globalUserMap.containsKey(key);
  }

}
