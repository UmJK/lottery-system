package com.poppulo.lottery;

import com.poppulo.lottery.model.Ticket;
import com.poppulo.lottery.repository.TicketRepository;
import com.poppulo.lottery.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    // Create a subclass to expose the protected method for testing purposes
    static class TestableTicketService extends TicketService {
        @Override
        public List<String> generateLines(int numberOfLines) {
            return super.generateLines(numberOfLines);
        }
    }

    @InjectMocks
    private TestableTicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        int lines = 3;
        Ticket ticket = new Ticket();
        ticket.setLines(ticketService.generateLines(lines));

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket createdTicket = ticketService.createTicket(lines);
        assertNotNull(createdTicket);
        assertEquals(lines, createdTicket.getLines().size());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testGetTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        Ticket foundTicket = ticketService.getTicket(ticketId);
        assertNotNull(foundTicket);
        assertEquals(ticketId, foundTicket.getId());

        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    void testAmendTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setStatusChecked(false);
        ticket.setLines(ticketService.generateLines(2));

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket amendedTicket = ticketService.amendTicket(ticketId, 3);
        assertNotNull(amendedTicket);
        assertEquals(5, amendedTicket.getLines().size());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testCannotAmendCheckedTicket() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setStatusChecked(true);

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        Ticket amendedTicket = ticketService.amendTicket(ticketId, 2);
        assertNull(amendedTicket);

        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void testCheckTicketStatus() {
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setStatusChecked(false);

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket checkedTicket = ticketService.checkTicketStatus(ticketId);
        assertNotNull(checkedTicket);
        assertTrue(checkedTicket.isStatusChecked());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void testGenerateLines() {
        int numberOfLines = 4;
        List<String> lines = ticketService.generateLines(numberOfLines);
        assertEquals(numberOfLines, lines.size());
    }
}