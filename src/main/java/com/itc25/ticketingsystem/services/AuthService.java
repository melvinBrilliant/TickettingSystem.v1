package com.itc25.ticketingsystem.services;

import com.itc25.ticketingsystem.dtos.user.RegistrationDto;
import com.itc25.ticketingsystem.enums.Role;
import com.itc25.ticketingsystem.models.Employee;
import com.itc25.ticketingsystem.models.User;
import com.itc25.ticketingsystem.repositories.EmployeeRepository;
import com.itc25.ticketingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthService {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(
            UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registration(RegistrationDto registrant){
        Employee employee = employeeRepository.findById(registrant.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee tidak ditemukan"));

        String employeeRoleId = employee.getId().substring(0, 1);

        Role role;
        switch (employeeRoleId) {
            case "T" -> role = Role.TECH_SUPPORT;
            case "M" -> role = Role.MANAGER;
            default -> role = Role.ADMIN;
        }

        User user = new User(employee, registrant.getUsername(), registrant.getPassword(), role.name());
        //employee.setUser(user);
        //user.setEmployee(employee);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        user.getPassword();

        return String.format("Username %s berhasil didaftarkan",
                registrant.getUsername());
    }
}
















