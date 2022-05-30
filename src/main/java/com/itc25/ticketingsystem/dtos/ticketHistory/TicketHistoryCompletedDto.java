package com.itc25.ticketingsystem.dtos.ticketHistory;

import com.itc25.ticketingsystem.models.Ticket;
import com.itc25.ticketingsystem.models.TicketHistory;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TicketHistoryCompletedDto {

    private final String description;
    private final String approvedById;

    public TicketHistory convert(Ticket ticket, String status){
        return new TicketHistory(
                ticket,
                description,
                LocalDate.now(),
                status,
                approvedById
        );
    }
}
