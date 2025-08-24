package com.Hm.hospital_patient.application.FileProcess;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.List;

import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.Model.UserDatabase;

public class ReadFile {
  private User user;
  private UserDatabase userDatabase;

  public HashMap<String, User> readAFile(File file) {
    userDatabase = new UserDatabase();
    user = new User();
    List<String> userData = readAllLine(file);

    if (userData.size() < 4) {
      System.out.println("File does not contain enough data. user deleted.");
      file.delete();
      return null;
    } else {
      user.setName(userData.get(0));
      user.setSurname(userData.get(1));
      user.setAge(Integer.parseInt(userData.get(2)));
      user.setPhone(userData.get(3));
    }

    String key = file.getName().toLowerCase(Locale.ENGLISH).replace(".txt", "");
    userDatabase.getUsers().put(key, user);
    return userDatabase.getUsers();
  }

  public UserDatabase readAllFile(List<File> files) {
    userDatabase = new UserDatabase();

    for (File file : files) {
      user = new User();
      List<String> userData = readAllLine(file);

      if (userData.size() < 4) {
        System.out.println("File does not contain enough data. user deleted.");
        file.delete();
        continue;
      } else {
        user.setName(userData.get(0));
        user.setSurname(userData.get(1));
        user.setAge(Integer.parseInt(userData.get(2)));
        user.setPhone(userData.get(3));
      }
      String key = file.getName().toLowerCase(Locale.ENGLISH).replace(".txt", "");
      userDatabase.getUsers().put(key, user);
    }

    return userDatabase;
  }

  public List<String> readAllLine(File file) {
    List<String> lines = new ArrayList<>();
    if (file == null || !file.exists()) {
      System.out.println("File does not exist or is null.");
      return lines;
    }
    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty()) {
          lines.add(line);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return lines;
  }
}
