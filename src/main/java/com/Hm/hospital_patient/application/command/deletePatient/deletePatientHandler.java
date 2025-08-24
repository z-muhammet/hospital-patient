package com.Hm.hospital_patient.application.command.deletePatient;

import an.awesome.pipelinr.Command;
import com.Hm.hospital_patient.application.Service.databaseService;
import org.springframework.stereotype.Component;

@Component
public class deletePatientHandler implements Command.Handler<deletePatientRequest, Boolean> {

  private final databaseService _dbService;

  public deletePatientHandler(databaseService dbService) {
    _dbService = dbService;
  }

  @Override
  public Boolean handle(deletePatientRequest command) {
    deletePatientDto dto = command.dto;

    if (dto.userKey == null || dto.userKey.isEmpty()) {
      return false;
    }
    try {
      _dbService.remove(dto.userKey);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}