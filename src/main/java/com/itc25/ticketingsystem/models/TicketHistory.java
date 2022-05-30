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
@Table(name = "TicketHistory")
public class TicketHistory {
    @Id
    @Column(name = "TikcetID", nullable = false, length = 15)
    private String id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TikcetID", nullable = false)
    private Ticket ticket;

    @Lob
    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "CompletedDate", nullable = false)
    private LocalDate completedDate;

    @Column(name = "Status", nullable = false, length = 7)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ApprovedBy", nullable = false)
    private Employee approvedBy;

    public TicketHistory(Ticket ticket, String description, LocalDate completedDate, String status,
                         String approvedBy) {
        this.ticket = ticket;
        this.description = description;
        this.completedDate = completedDate;
        this.status = status;
        this.approvedBy = new Employee(approvedBy);
    }
}