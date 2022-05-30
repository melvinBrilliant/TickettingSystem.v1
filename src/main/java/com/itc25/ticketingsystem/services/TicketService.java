package com.itc25.ticketingsystem.services;

import com.itc25.ticketingsystem.dtos.ticket.TicketHeaderDto;
import com.itc25.ticketingsystem.dtos.ticket.TicketInsertDto;
import com.itc25.ticketingsystem.dtos.ticketHistory.TicketHistoryCompletedDto;
import com.itc25.ticketingsystem.dtos.ticketHistory.TicketHistoryHeaderDto;
import com.itc25.ticketingsystem.models.Ticket;
import com.itc25.ticketingsystem.models.TicketHistory;
import com.itc25.ticketingsystem.repositories.TicketHistoryRepository;
import com.itc25.ticketingsystem.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketHistoryRepository ticketHistoryRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketHistoryRepository ticketHistoryRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketHistoryRepository = ticketHistoryRepository;
    }

    public List<TicketHeaderDto> findAllTicket() {
        return TicketHeaderDto.toList(ticketRepository.findAll());
    }

    public List<TicketHeaderDto> insertNewTicket(TicketInsertDto newTicket) {
        Ticket latestTicketId = ticketRepository.getLatestTicketId();
        Ticket ticket = newTicket.convert(latestTicketId);
        ticketRepository.save(ticket);
        return TicketHeaderDto.toList(ticketRepository.findAll());
    }

    public List<TicketHeaderDto> cancelTicket(String id){
        Ticket oldTicket =ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket tidak ditemukan"));

        oldTicket.setStatus("CANCELLED");

        ticketRepository.save(oldTicket);
        return TicketHeaderDto.toList(ticketRepository.findAll());
    }

    public List<TicketHistoryHeaderDto> findAllHistory(){
        return TicketHistoryHeaderDto.toList(ticketHistoryRepository.findAll());
    }

    public List<TicketHistoryHeaderDto> approveComplete(String ticketId, TicketHistoryCompletedDto newHistory){
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Tiket tidak ditemukan"));

        LocalDate ticketDueDate = ticket.getDueDate();

        int duration = (int) ChronoUnit.DAYS.between(LocalDate.now(), ticketDueDate);

        TicketHistory history;
        if (duration < 0) {
            history = newHistory.convert(ticket, "OVERDUE");
        }else {
            history = newHistory.convert(ticket, "ON_TIME");
        }

        ticket.setStatus("COMPLETED");
        ticketRepository.save(ticket);
        ticketHistoryRepository.save(history);

        return TicketHistoryHeaderDto.toList(ticketHistoryRepository.findAll());
    }

    public List<TicketHeaderDto> findAllTicketsByYear(int year){
        List<Ticket> tickets = ticketRepository.findAllTicketsByYear(year);
        return TicketHeaderDto.toList(tickets);
    }

    public List<TicketHeaderDto> findAllTicketsByStatus(String status){
        String inputStatus = status.trim().toUpperCase();
        List<Ticket> tickets = ticketRepository.findAllTicketsByStatus(inputStatus);

        if (
                inputStatus.equals("IN_PROGRESS") ||
                        inputStatus.equals("COMPLETED") ||
                        inputStatus.equals("CANCELED")
        ){
            return TicketHeaderDto.toList(tickets);
        }else {
            throw new EntityNotFoundException("Input kriteria status tidak valid");
        }
    }

    public List<TicketHeaderDto> findAllTicketByUrgency(){
        List<Ticket> tickets = ticketRepository.findAllTicketByUrgency();
        return TicketHeaderDto.toList(tickets);
    }
}