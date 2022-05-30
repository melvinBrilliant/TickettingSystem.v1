package com.itc25.ticketingsystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
// @Setter
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "EmployeeID", nullable = false, length = 3)
    private String id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "BirthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "Phone", nullable = false, length = 15)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "JobID", nullable = false)
    private Job jobID;

    @OneToMany(mappedBy = "appointedTo")
    private Set<Ticket> tickets = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    private User user;

    @OneToMany(mappedBy = "approvedBy")
    private Set<TicketHistory> ticketHistories = new LinkedHashSet<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setJobID(Integer jobID) {
        this.jobID = new Job(jobID);
    }

    public Employee(String id, String firstName, String lastName, LocalDate birthDate, String phone, Integer jobID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.jobID = new Job(jobID);
    }

    public Employee(String firstName, String lastName, LocalDate birthDate, String phone, Integer jobID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.jobID = new Job(jobID);
    }

    public Employee(String id) {
        this.id = id;
    }

    public String fetchFullName() {
        return String.format("%s %s", this.firstName, this.lastName == null?
                "" : this.lastName);
    }

    public String findId(){
        return this.id;
    }
}