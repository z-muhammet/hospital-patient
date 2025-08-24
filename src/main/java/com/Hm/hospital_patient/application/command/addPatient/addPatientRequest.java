package com.Hm.hospital_patient.application.command.addPatient;

import an.awesome.pipelinr.Command;

public class addPatientRequest implements Command<String> {
  public addPatientDto dto;

  public addPatientRequest(addPatientDto dto) {
    this.dto = dto;
  }
}
