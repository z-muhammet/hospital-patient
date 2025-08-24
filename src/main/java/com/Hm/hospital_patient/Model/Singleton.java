package com.Hm.hospital_patient.Model;

import java.util.Map;

import java.util.HashMap;

public class Singleton {

  private static Map<String, User> globalUserMap;

  private Singleton() {
  }

  public synchronized static Map<String, User> getGlobalUserMap() {
    if (globalUserMap == null) {
      synchronized (Singleton.class) {
        if (globalUserMap == null) {
          globalUserMap = new HashMap<>();
        }
      }
    }
    return globalUserMap;
  }
}
