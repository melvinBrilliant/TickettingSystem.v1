package com.itc25.ticketingsystem.dtos.ticketHistory;

import com.itc25.ticketingsystem.models.TicketHistory;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TicketHistoryHeaderDto {
    private final String id;;
    private final String description;
    private final LocalDate completedDate;
    private final String status;
    private final String approvedBy;

    public static List<TicketHistoryHeaderDto> toList(List<TicketHistory> ticketHistories){
        List<TicketHistoryHeaderDto> result = new ArrayList<>();

        for (TicketHistory history : ticketHistories) {
            result.add(set(history));
        }

        return result;
    }

    public static TicketHistoryHeaderDto set(TicketHistory history){
        return new TicketHistoryHeaderDto(
                history.getTicket().getId(),
                history.getDescription(),
                history.getCompletedDate(),
                history.getStatus(),
                history.getApprovedBy().getId()
        );
    }
}
