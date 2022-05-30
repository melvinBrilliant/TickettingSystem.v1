package com.itc25.ticketingsystem.dtos.ticket;

import com.itc25.ticketingsystem.models.Ticket;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TicketHeaderDto {
    private final String id;
    private final String title;
    private final String details;
    private final LocalDate requestDate;
    private final LocalDate dueDate;
    private final String urgency;
    private final String createdById;
    private final String appointedToId;
    private final String status;

    public static List<TicketHeaderDto> toList(List<Ticket> tickets){
        List<TicketHeaderDto> result = new ArrayList<>();

        for (Ticket ticket : tickets) {
            result.add(set(ticket));
        }

        return result;
    }

    public static TicketHeaderDto set(Ticket ticket){
        return new TicketHeaderDto(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDetails(),
                ticket.getRequestDate(),
                ticket.getDueDate(),
                ticket.getUrgency(),
                ticket.getCreatedBy().getId(),
                ticket.getAppointedTo().getId(),
                ticket.getStatus()
        );
    }
}
