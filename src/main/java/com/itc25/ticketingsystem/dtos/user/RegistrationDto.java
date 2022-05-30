package com.itc25.ticketingsystem.dtos.user;

import lombok.Data;

@Data
public class RegistrationDto {

    private final String employeeId;
    private final String username;
    private final String password;
}
