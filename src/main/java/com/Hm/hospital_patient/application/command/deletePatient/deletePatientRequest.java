package com.Hm.hospital_patient.application.command.deletePatient;

import an.awesome.pipelinr.Command;

public class deletePatientRequest implements Command<Boolean> {
  public deletePatientDto dto;

  public deletePatientRequest(deletePatientDto dto) {
    this.dto = dto;
  }

  public deletePatientRequest() {
  }
}