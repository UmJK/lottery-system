package com.poppulo.lottery.controller;

import com.poppulo.lottery.dto.TicketRequest;
import com.poppulo.lottery.model.Ticket;
import com.poppulo.lottery.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequest request) {
        return ticketService.createTicket(request.getNumberOfLines());
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PutMapping("/{id}")
    public Ticket amendTicket(@PathVariable Long id, @RequestBody TicketRequest request) {
        return ticketService.amendTicket(id, request.getNumberOfLines());
    }

    @PutMapping("/status/{id}")
    public Ticket checkTicketStatus(@PathVariable Long id) {
        return ticketService.checkTicketStatus(id);
    }
}

//	Endpoints:
//            •	POST /ticket: Create a new ticket with the specified number of lines.
//            •	GET /ticket: Retrieve all tickets.
//            •	GET /ticket/{id}: Retrieve a specific ticket by its ID.
//            •	PUT /ticket/{id}: Amend an existing ticket with new lines, provided it hasn’t been checked.
//            •	PUT /status/{id}: Check and lock the status of a specific ticket.