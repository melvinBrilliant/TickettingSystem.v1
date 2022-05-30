package com.itc25.ticketingsystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "\"User\"")
public class User {
    @Id
    @Column(name = "UserID", nullable = false, length = 3)
    private String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    private Employee employee;

    @Column(name = "Username", nullable = false, length = 200)
    private String username;

    @Column(name = "Password", nullable = false, length = 200)
    private String password;

    @Column(name = "Enabled", nullable = false)
    private Boolean enabled = false;

    @Column(name = "Role", nullable = false, length = 50)
    private String role;

    public User(Employee employee, String username, String password, String role) {
        this.employee = employee;
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.role = role;
    }
}