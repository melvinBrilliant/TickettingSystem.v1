package com.itc25.ticketingsystem.services;

import com.itc25.ticketingsystem.dtos.employee.EmployeeHeaderDto;
import com.itc25.ticketingsystem.dtos.employee.EmployeeInsertDto;
import com.itc25.ticketingsystem.dtos.employee.EmployeeUpdateDto;
import com.itc25.ticketingsystem.models.Employee;
import com.itc25.ticketingsystem.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    // findAll
    public List<EmployeeHeaderDto> findAllEmployee(){
        return EmployeeHeaderDto.toList(employeeRepository.findAll());
    }

    // insert new Employee
    public List<EmployeeHeaderDto> insertNewEmployee(EmployeeInsertDto newEmployee){
        Employee employee = newEmployee.convert();
        employeeRepository.save(employee);
        return EmployeeHeaderDto.toList(employeeRepository.findAll());
    }

    // update employee
    public List<EmployeeHeaderDto> updateEmployee(String id, EmployeeUpdateDto updateEmployee){
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee dengan ID tersebut tidak ada"));

        oldEmployee.setFirstName(updateEmployee.getFirstName() == null?
                oldEmployee.getFirstName() : updateEmployee.getFirstName());
        oldEmployee.setLastName(updateEmployee.getLastName() == null?
                oldEmployee.getLastName() : updateEmployee.getLastName());
        oldEmployee.setBirthDate(updateEmployee.getBirthDate() == null?
                oldEmployee.getBirthDate() : updateEmployee.getBirthDate());
        oldEmployee.setPhone(updateEmployee.getPhone() == null?
                oldEmployee.getPhone() : updateEmployee.getPhone());
        oldEmployee.setJobID(updateEmployee.getJobID() == null?
                oldEmployee.getJobID().getId() : updateEmployee.getJobID());

        employeeRepository.save(oldEmployee);
        return EmployeeHeaderDto.toList(employeeRepository.findAll());
    }

    // delete employee
    public List<EmployeeHeaderDto> deleteEmployeeById(String id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee dengan ID tersebut tidak ada"));

        employeeRepository.delete(employee);
        return EmployeeHeaderDto.toList(employeeRepository.findAll());
    }

    // find all technical support
    public List<EmployeeHeaderDto> findAllTechnicalSupport(){
        return EmployeeHeaderDto.toList(employeeRepository.findAllTechnicalSupport());
    }

    // find all technical support on duty
    public List<EmployeeHeaderDto> findAllTechnicalSupportOnDuty(){
        return EmployeeHeaderDto.toList(employeeRepository.findAllTechnicalSupportOnDuty());
    }

    // find all technical support off duty
    public List<EmployeeHeaderDto> findAllTehcnicalSupportOffDuty(){
        return EmployeeHeaderDto.toList(employeeRepository.findAllTechnicalSupportOffDuty());
    }
}
