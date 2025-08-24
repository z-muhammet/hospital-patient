package com.Hm.hospital_patient.application.query.getpatientbyid;

import com.Hm.hospital_patient.Model.User;

import an.awesome.pipelinr.Command;

public class getPatientByIdRequest implements Command<User> {
  public getPatientByIdDto dto;

  public getPatientByIdRequest(getPatientByIdDto dto) {
    this.dto = dto;
  }

  public getPatientByIdRequest() {
  }

}
