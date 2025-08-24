package com.Hm.hospital_patient.application.command.addPatient;

public class addPatientDto {
  public String name;
  public String surname;
  public Integer age;
  public String phone;
  public String email;

  public addPatientDto(String name, String surname, Integer age, String phone, String email) {
    this.name = name;
    this.surname = surname;
    this.age = age;
    this.phone = phone;
    this.email = email;
  }

  public addPatientDto() {
  } // Jackson / Spring için boş constructor
}