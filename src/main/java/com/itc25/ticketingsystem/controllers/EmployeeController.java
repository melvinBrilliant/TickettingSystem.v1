package com.itc25.ticketingsystem.controllers;

import com.itc25.ticketingsystem.dtos.RestResponse;
import com.itc25.ticketingsystem.dtos.employee.EmployeeHeaderDto;
import com.itc25.ticketingsystem.dtos.employee.EmployeeInsertDto;
import com.itc25.ticketingsystem.dtos.employee.EmployeeUpdateDto;
import com.itc25.ticketingsystem.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    // no. 1
    @GetMapping
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> findAllEmployee(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllEmployee(),
                        "Berhasil menampilkan semua employee",
                        "200"
                ));
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> insertNewEmployee(
            @RequestBody EmployeeInsertDto newEmployee
            ){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.insertNewEmployee(newEmployee),
                        "Berhasil memasukkan employee baru",
                        "200"
                ));
    }

    @PutMapping("update/{employeeId}")
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> updateEmployee(
            @PathVariable String employeeId, @RequestBody EmployeeUpdateDto updatedEmployee
    ){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.updateEmployee(employeeId, updatedEmployee),
                        "Berhasil memperbarui employee",
                        "200"
                ));
    }

    @DeleteMapping("delete/{employeeId}")
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> deleteEmployee(
            @PathVariable String employeeId
    ){
        return ResponseEntity.accepted()
                .body(new RestResponse<>(
                        service.deleteEmployeeById(employeeId),
                        "Berhasil menghapus employee",
                        "202"
                ));
    }

    @GetMapping("technicalSupport")
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> findAllTechnicalSupport(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllTechnicalSupport(),
                        "Berhasil menampilkan semua technical support",
                        "200"
                ));
    }

    @GetMapping("technicalSupport/onDuty")
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> findAllTechnicalSupportOnDuty(){
        if (service.findAllTechnicalSupportOnDuty().size() == 0){
            throw new EntityNotFoundException("Tidak terdapat technical support yang sedang bertugas");
        }else {
            return ResponseEntity.ok()
                    .body(new RestResponse<>(
                            service.findAllTechnicalSupportOnDuty(),
                            "Berhasil menampilkan semua technical support yang sedang bertugas",
                            "200"
                    ));
        }
    }

    @GetMapping("technicalSupport/offDuty")
    public ResponseEntity<RestResponse<List<EmployeeHeaderDto>>> findAllTechnicalSupportOffDuty(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllTehcnicalSupportOffDuty(),
                        "Berhasil menampilkan semua technical support yang tidak bertugas",
                        "200"
                ));
    }
}
