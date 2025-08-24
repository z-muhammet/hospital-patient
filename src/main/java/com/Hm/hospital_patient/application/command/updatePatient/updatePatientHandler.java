package com.Hm.hospital_patient.application.command.updatePatient;

import org.springframework.stereotype.Component;

import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.application.Service.databaseService;

import an.awesome.pipelinr.Command;

@Component
public class updatePatientHandler implements Command.Handler<updatePatientRequest, String> {

  private final databaseService _updateDatabaseService;

  public updatePatientHandler(databaseService updatePatientService) {
    _updateDatabaseService = updatePatientService;
  }

  @Override
  public String handle(updatePatientRequest command) {
    updatePatientDto dto = command.dto;

    if (dto != null && dto.Id != null && !dto.Id.isEmpty()) {
      _updateDatabaseService.put(dto.Id, dtoToUser(dto));
      return "Patient updated successfully"; // Added return statement
    } else {
      throw new IllegalArgumentException("Invalid patient ID");
    }

  }

  public User dtoToUser(updatePatientDto dto) {
    User user = new User();
    user.setName(dto.name);
    user.setSurname(dto.surname);
    user.setAge(dto.age);
    user.setPhone(dto.phone);
    user.setEmail(dto.email);
    return user;
  }

}
