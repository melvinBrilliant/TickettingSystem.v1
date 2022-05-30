package com.itc25.ticketingsystem.dtos.ticket;

import com.itc25.ticketingsystem.models.Ticket;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class TicketInsertDto {
    private final String title;
    private final String details;
    private final String requestDate;
    private final String dueDate;
    private final String urgency;
    private final String createdById;
    private final String appointedToId;

    private String newTicketId(Ticket latestTicket){
        String latestTikcetId = latestTicket.getId();
        String thisYear = String.valueOf(LocalDate.now().getYear());

        String latestTicketYear = latestTikcetId.substring(4, 8);
        int latestTicketNumber = Integer.parseInt(latestTikcetId.substring(9));
        int newIdNumber = latestTicketNumber + 1;

        String result;
        if (thisYear.equals(latestTicketYear)){
            result = String.format("SRQ/%s/%d", thisYear, newIdNumber);
        }else {
            result = String.format("SRQ/%s/1", thisYear);
        }

        return result;
    }

    public Ticket convert(Ticket latestTikcet){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Ticket(
                newTicketId(latestTikcet),
                title,
                details,
                LocalDate.parse(requestDate, formatDate),
                LocalDate.parse(dueDate, formatDate),
                urgency,
                "IN_PROGRESS",
                createdById,
                appointedToId
        );
    }
}
