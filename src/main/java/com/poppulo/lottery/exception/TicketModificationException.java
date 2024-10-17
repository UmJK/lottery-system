package com.poppulo.lottery.exception;

public class TicketModificationException extends RuntimeException {
    public TicketModificationException(Long id) {
        super("Cannot modify the ticket with id: " + id + " because it has already been checked.");
    }
}