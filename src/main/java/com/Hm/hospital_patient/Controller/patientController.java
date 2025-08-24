package com.Hm.hospital_patient.Controller;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.repack.org.checkerframework.checker.units.qual.s;

import com.Hm.hospital_patient.Model.User;
import com.Hm.hospital_patient.application.*;
import com.Hm.hospital_patient.application.command.addPatient.addPatientDto;
import com.Hm.hospital_patient.application.command.addPatient.addPatientRequest;
import com.Hm.hospital_patient.application.command.deletePatient.deletePatientDto;
import com.Hm.hospital_patient.application.command.deletePatient.deletePatientRequest;
import com.Hm.hospital_patient.application.command.updatePatient.updatePatientDto;
import com.Hm.hospital_patient.application.command.updatePatient.updatePatientRequest;
import com.Hm.hospital_patient.application.query.getallpatients.getAllPatientsRequest;
import com.Hm.hospital_patient.application.query.getpatientbyid.getPatientByIdDto;
import com.Hm.hospital_patient.application.query.getpatientbyid.getPatientByIdRequest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/patients")
public class patientController {

  private final Pipeline _pipeline;

  public patientController(Pipeline pipeline) {
    _pipeline = pipeline;
  }

  @PostMapping
  public ResponseEntity<String> putPatient(@RequestBody addPatientDto dto) {
    addPatientRequest command = new addPatientRequest(dto);
    String patientId = command.execute(_pipeline);
    return ResponseEntity.ok(patientId);
  }

  @PutMapping
  public ResponseEntity<String> putPatient(@RequestBody updatePatientDto dto) {
    updatePatientRequest command = new updatePatientRequest(dto);
    String result = command.execute(_pipeline);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getPatient(@PathVariable String id) {
    getPatientByIdRequest command = new getPatientByIdRequest(new getPatientByIdDto(id));
    User patient = command.execute(_pipeline);
    if (patient == null) {
      return ResponseEntity.status(404).body(null);
    }
    return ResponseEntity.ok(patient);
  }

  @GetMapping
  public Map<String, User> getAllPatient() {
    getAllPatientsRequest request = new getAllPatientsRequest(); // dto boşsa default ctor kullan
    return _pipeline.send(request);
  }

  @DeleteMapping
  public ResponseEntity<String> deletePatient(@RequestParam String id) {
    deletePatientRequest command = new deletePatientRequest(new deletePatientDto(id));
    Boolean result = command.execute(_pipeline);
    if (result == null || !result) {
      return ResponseEntity.status(404).body("Patient not found");
    }
    return ResponseEntity.ok("Patient deleted successfully");
  }

}
