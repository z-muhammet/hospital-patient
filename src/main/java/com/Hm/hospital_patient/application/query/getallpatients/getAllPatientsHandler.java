package com.Hm.hospital_patient.application.query.getallpatients;

import org.springframework.stereotype.Component;
import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.application.Service.databaseService;
import an.awesome.pipelinr.Command;
import java.util.Map;

import javax.naming.NameNotFoundException;

@Component
public class getAllPatientsHandler implements Command.Handler<getAllPatientsRequest, Map<String, User>> {

  private final databaseService _dbService;

  public getAllPatientsHandler(databaseService dbService) {
    _dbService = dbService;
  }

  @Override
  public Map<String, User> handle(getAllPatientsRequest command) {
    Map<String, User> allMap = _dbService.getMap();
    if (allMap == null || allMap.isEmpty()) {
      throw new IllegalArgumentException("Invalid Database or no patients found");
    }
    return allMap;
  }
}