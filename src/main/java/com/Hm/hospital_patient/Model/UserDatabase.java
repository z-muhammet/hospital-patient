package com.Hm.hospital_patient.Model;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDatabase {
  HashMap<String, User> users = new HashMap<String, User>();
}
