package com.itc25.ticketingsystem.dtos.employee;

import com.itc25.ticketingsystem.models.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class EmployeeUpdateDto {

    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String phone;
    private final Integer jobID;

}
