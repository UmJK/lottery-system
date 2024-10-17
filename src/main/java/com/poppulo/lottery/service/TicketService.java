package com.poppulo.lottery.service;

import com.poppulo.lottery.model.Ticket;
import com.poppulo.lottery.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(int numberOfLines) {
        Ticket ticket = new Ticket();
        ticket.setLines(generateLines(numberOfLines));
        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket amendTicket(Long id, int numberOfLines) {
        Ticket ticket = getTicket(id);
        if (ticket != null && !ticket.isStatusChecked()) {
            List<String> newLines = generateLines(numberOfLines);
            ticket.getLines().addAll(newLines);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public Ticket checkTicketStatus(Long id) {
        Ticket ticket = getTicket(id);
        if (ticket != null && !ticket.isStatusChecked()) {
            ticket.setStatusChecked(true);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    /**
     * Generates a specified number of lines, each containing three random numbers (0, 1, or 2).
     * @param numberOfLines The number of lines to generate.
     * @return List of generated lines.
     */
    protected List<String> generateLines(int numberOfLines) {
        Random random = new Random();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) {
            // Each line contains 3 random numbers between 0 and 2
            lines.add(random.nextInt(3) + "," + random.nextInt(3) + "," + random.nextInt(3));
        }
        return lines;
    }
}