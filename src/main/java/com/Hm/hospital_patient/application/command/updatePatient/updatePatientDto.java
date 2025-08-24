package com.Hm.hospital_patient.application.command.updatePatient;

public class updatePatientDto {
  public String Id;
  public String name = null;
  public String surname = null;
  public Integer age = 0;
  public String phone = null;
  public String email = null;

  public updatePatientDto(String Id, String name, String surname, Integer age, String phone, String email) {
    this.Id = Id;
    this.name = name;
    this.surname = surname;
    this.age = age;
    this.phone = phone;
    this.email = email;
  }

}
