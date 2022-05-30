package com.itc25.ticketingsystem.dtos.employee;

import com.itc25.ticketingsystem.models.Employee;
import lombok.Data;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeHeaderDto {

    private final String id;
    private final String fullName;
    private final LocalDate birthDate;
    private final String phone;
    private final String title;

    public static EmployeeHeaderDto set(Employee employee){
        return new EmployeeHeaderDto(
                employee.findId(),
                employee.fetchFullName(),
                employee.getBirthDate(),
                employee.getPhone(),
                employee.getJobID().getTitle()
        );
    }

    public static List<EmployeeHeaderDto> toList(List<Employee> employees){
        List<EmployeeHeaderDto> result = new ArrayList<>();

        if (employees.size() == 0){
            throw new EntityNotFoundException("Employee tidak ditemukan");
        }

        for (Employee employee : employees) {
            result.add(set(employee));
        }

        return result;
    }
}
