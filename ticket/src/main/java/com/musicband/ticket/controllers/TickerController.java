package com.musicband.ticket.controllers;

import com.musicband.ticket.dto.TicketDto;
import com.musicband.ticket.dto.TicketOrderDto;
import com.musicband.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/ticket")
@RequiredArgsConstructor
public class TickerController {

    private final TicketService ticketService;

    @GetMapping("find-all-tickets")
    public ResponseEntity<List<TicketDto>> findAllTickets() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.getTickets());
    }

    @GetMapping("find-all-available-tickets")
    public ResponseEntity<List<TicketDto>> findAllAvailableTickets() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.getAvailableTickets());
    }

    @PostMapping("add-ticket")
    public ResponseEntity<String> addTicket(@RequestBody TicketDto ticketDto) {
        ticketService.addTicket(ticketDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully added ticket");
    }

    @PostMapping("order-ticket")
    public ResponseEntity<String> orderTicket(@RequestBody TicketOrderDto ticketOrderDto) {
        ticketService.orderTicket(ticketOrderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order created successfully");
    }
}
