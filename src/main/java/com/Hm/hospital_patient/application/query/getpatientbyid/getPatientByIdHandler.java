package com.Hm.hospital_patient.application.query.getpatientbyid;

import org.springframework.stereotype.Component;

import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.application.Service.databaseService;

import an.awesome.pipelinr.Command;

@Component
public class getPatientByIdHandler implements Command.Handler<getPatientByIdRequest, User> {

  private final databaseService _dbService;

  public getPatientByIdHandler(databaseService dbService) {
    _dbService = dbService;
  }

  @Override
  public User handle(getPatientByIdRequest command) {
    getPatientByIdDto dto = command.dto;

    if (dto == null || dto.id == null || dto.id.isEmpty()) {
      throw new IllegalArgumentException("Invalid patient ID");
    }

    User patient = _dbService.get(dto.id);

    if (patient == null) {
      throw new IllegalArgumentException("Patient not found");
    }

    return patient;
  }
}