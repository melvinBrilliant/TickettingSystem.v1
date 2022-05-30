package com.itc25.ticketingsystem.controllers;

import com.itc25.ticketingsystem.dtos.RestResponse;
import com.itc25.ticketingsystem.dtos.ticket.TicketHeaderDto;
import com.itc25.ticketingsystem.dtos.ticket.TicketInsertDto;
import com.itc25.ticketingsystem.dtos.ticketHistory.TicketHistoryCompletedDto;
import com.itc25.ticketingsystem.dtos.ticketHistory.TicketHistoryHeaderDto;
import com.itc25.ticketingsystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    private TicketService service;

    @Autowired
    public TicketController(TicketService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<TicketHeaderDto>>> findAllTicket(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllTicket(),
                        "Berhasil menampilkan seluruh ticket",
                        "200"
                ));
    }

    // nomor 2
    @PostMapping("insert")
    public ResponseEntity<RestResponse<List<TicketHeaderDto>>> insertNewTicket(
            @RequestBody TicketInsertDto newTicket){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.insertNewTicket(newTicket),
                        "Berhasil menambahkan ticket baru",
                        "200"
                ));
    }

    // nomor 3
    @PutMapping("cancel")
    public ResponseEntity<RestResponse<List<TicketHeaderDto>>> cancelTicket(
            @RequestParam String ticketId
    ){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.cancelTicket(ticketId),
                        "Berhasil cancel ticket",
                        "200"
                ));
    }

    // nomor 4
    @PostMapping("complete")
    public ResponseEntity<RestResponse<List<TicketHistoryHeaderDto>>> approveComplete(
            @RequestParam String ticketId, @RequestBody TicketHistoryCompletedDto newHistory
    ){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.approveComplete(ticketId, newHistory),
                        "Berhasil melaporkan ticket complete",
                        "200"
                ));
    }

    // nomor 5a
    @GetMapping("year")
    public ResponseEntity<RestResponse<List<TicketHeaderDto>>> findTicketsByYear(
            @RequestParam int year
    ){
        String message = String.format("Berhasil menampilkan tiket pada tahun %d", year);

        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllTicketsByYear(year),
                        message,
                        "200"
                ));
    }

    @GetMapping("status")
    public ResponseEntity<RestResponse<List<TicketHeaderDto>>> findTicketsByStatus(
            @RequestParam String status
    ){
        String message = String.format("Berhasil menampilkan tiket dengan status %s", status);

        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllTicketsByStatus(status),
                        message,
                        "200"
                ));
    }

    @GetMapping("urgency")
    public ResponseEntity<RestResponse<List<TicketHeaderDto>>> findTicketsByUrgency(){
        String message = "Berhasil menampilkan tiket diurutkan dengan tingkat urgency";

        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllTicketByUrgency(),
                        message,
                        "200"
                ));
    }
}
