package com.itc25.ticketingsystem.repositories;

import com.itc25.ticketingsystem.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(value = """
            select top 1 *
            from Ticket
            order by TicketID desc
            """, nativeQuery = true)
    Ticket getLatestTicketId();

    // untuk 5a
    @Query(value = """
            select *
            from Ticket
            where YEAR(RequestDate) = :year
            """, nativeQuery = true)
    List<Ticket> findAllTicketsByYear(@Param("year") int year);

    // untuk 5b
    @Query(value = """
            select *
            from Ticket
            where Status = :status
            """, nativeQuery = true)
    List<Ticket> findAllTicketsByStatus(@Param("status") String status);

    // untuk 5c
    @Query(value = """
            select
            	tt.TicketID, tt.Title, tt.Details, tt.RequestDate,
            	tt.DueDate, tt.Urgency, tt.[Status], tt.CreatedBy,
            	tt.AppointedTo
            from (
            	select *,
            		case
            			when Urgency = 'HIGH' then 1
            			when Urgency = 'MEDIUM' then 2
            			when Urgency = 'LOW' then 3
            		end as t
            	from Ticket
            ) tt
            """, nativeQuery = true)
    List<Ticket> findAllTicketByUrgency();
}
