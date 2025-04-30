package com.musicband.ticket.controllers;

import com.musicband.ticket.dto.TicketDto;
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

    @GetMapping("find-all-available-tickets")
    public ResponseEntity<List<TicketDto>> findAllAvailableTickets() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ticketService.getTickets());
    }

    @PatchMapping("purchase-ticket")
    public ResponseEntity<String> purchaseTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.purchaseTicket(ticketDto)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully purchased ticket")
                : ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Cannot purchase ticket");
    }

    @PatchMapping("return-ticket")
    public ResponseEntity<String> returnTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.returnTicket(ticketDto)
                ? ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully returned ticket")
                : ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Cannot returned ticket");
    }

    @PostMapping("add-ticket")
    public ResponseEntity<String> addTicket(@RequestBody TicketDto ticketDto) {
        ticketService.addTicket(ticketDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully added ticket");
    }
}
