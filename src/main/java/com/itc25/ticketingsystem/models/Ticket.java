package com.itc25.ticketingsystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Ticket")
public class Ticket {
    @Id
    @Column(name = "TicketID", nullable = false, length = 15)
    private String id;

    @Lob
    @Column(name = "Title", nullable = false)
    private String title;

    @Lob
    @Column(name = "Details", nullable = false)
    private String details;

    @Column(name = "RequestDate", nullable = false)
    private LocalDate requestDate;

    @Column(name = "DueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "Urgency", nullable = false, length = 6)
    private String urgency;

    @Column(name = "Status", nullable = false, length = 11)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CreatedBy", nullable = false)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AppointedTo", nullable = false)
    private Employee appointedTo;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket")
    private TicketHistory ticketHistory;

    public Ticket(String id, String title, String details, LocalDate requestDate, LocalDate dueDate, String urgency,
                  String status, String createdBy, String appointedTo) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.urgency = urgency;
        this.status = status;
        this.createdBy = new Employee(createdBy);
        this.appointedTo = new Employee(appointedTo);
    }


    public Ticket(String id) {
        this.id = id;
    }
}