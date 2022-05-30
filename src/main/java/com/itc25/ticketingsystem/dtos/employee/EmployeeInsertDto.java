package com.itc25.ticketingsystem.dtos.employee;

import com.itc25.ticketingsystem.models.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class EmployeeInsertDto {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String birthDate;
    private final String phone;
    private final Integer jobID;

    public Employee convert(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new Employee(
                id,
                firstName,
                lastName,
                LocalDate.parse(birthDate, formatDate),
                phone,
                jobID
        );
    }
}
