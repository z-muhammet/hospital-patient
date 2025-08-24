package com.Hm.hospital_patient.application.command.updatePatient;

import an.awesome.pipelinr.Command;

public class updatePatientRequest implements Command<String> {
  public updatePatientDto dto;

  public updatePatientRequest(updatePatientDto dto) {
    this.dto = dto;
  }

  public updatePatientRequest() {
  }

}
