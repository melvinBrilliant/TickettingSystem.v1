package com.itc25.ticketingsystem.repositories;

import com.itc25.ticketingsystem.models.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, String> {
}
