package com.Hm.hospital_patient.application.Service;

import org.springframework.stereotype.Component;

import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.Model.UserDatabase;
import com.Hm.hospital_patient.application.FileProcess.ReadFile;
import com.Hm.hospital_patient.application.FileProcess.SearchFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class databaseService {
  SearchFile searchFile = new SearchFile();
  ReadFile ReadFile = new ReadFile();

  Map<String, User> globalUserMap = new HashMap<>();
  UserDatabase db = new UserDatabase();

  public databaseService() {
    List<File> allFiles = searchFile.getAllFiles();
    db = ReadFile.readAllFile(allFiles);
    globalUserMap = db.getUsers();
  }

  public Map<String, User> getMap() {
    return globalUserMap;
  }

  public Object get(String key) {
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
    searchFile.getFilesByName(key).delete();
  }

  public boolean containsKey(String key) {
    return globalUserMap.containsKey(key);
  }

}
