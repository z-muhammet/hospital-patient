package com.Hm.hospital_patient.application.command.addPatient;

import an.awesome.pipelinr.Command;
import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.application.FileProcess.SearchFile;
import com.Hm.hospital_patient.application.Service.databaseService;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class addPatientHandler implements Command.Handler<addPatientRequest, String> {

  private final databaseService dbService;

  private SearchFile searchFile = new SearchFile();
  private String userKey = UUID.randomUUID().toString().substring(0, 8).toLowerCase();

  public addPatientHandler(databaseService dbService) {
    this.dbService = dbService;
  }

  @Override
  public String handle(addPatientRequest command) {
    addPatientDto dto = command.dto;
    Boolean isValid = true;
    User user = new User();
    user.setName(null != dto.name ? dto.name : "Unknown");
    user.setSurname(null != dto.surname ? dto.surname : "Unknown");
    user.setAge(null != dto.age ? dto.age : 0);
    user.setPhone(null != dto.phone ? dto.phone : "Unknown");
    user.setEmail(null != dto.email ? dto.email : "Unknown");

    try {
      while (isValid) {
        userKey = UUID.randomUUID().toString().substring(0, 8).toLowerCase();
        isValid = searchFile.getFilesByName(userKey) != null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    dbService.put(userKey, user);

    return userKey;
  }
}
