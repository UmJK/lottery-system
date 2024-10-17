package com.poppulo.lottery.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket not found with id: " + id);
    }
}