package com.andersen.ticketservice;

import lesson10.exception.TicketNotFoundException;
import lesson10.model.Ticket;
import lesson10.repository.TicketRepository;
import lesson10.service.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


public class TicketServiceTest {

    private static TicketService ticketService;
    private static TicketRepository ticketRepository;

    @BeforeAll
    public static void init() {
        ticketRepository = mock(TicketRepository.class);
        ticketService = new TicketService(ticketRepository);
    }

    @Test
    public void createTicket_ValidTicket_Saved() {
        Ticket ticketToSave = new Ticket();
        when(ticketRepository.save(ticketToSave.getUserId(),ticketToSave.getTicketType(),ticketToSave.getCreationDate()))
                .thenReturn(1);
        Ticket savedTicket = ticketService.createTicket(ticketToSave);
        assertEquals(ticketToSave,savedTicket);
    }

    @Test
    public void createTicket_NotExistedUser_NotSaved() {
        Ticket ticketToSave = new Ticket();
        ticketToSave.setUserId(28L);
        when(ticketRepository.save(ticketToSave.getUserId(),ticketToSave.getTicketType(),ticketToSave.getCreationDate()))
                .thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () -> ticketService.createTicket(ticketToSave));
    }

    @Test
    public void createTicket_ExistedTicketType_Saved() {
        Ticket ticketToSave = new Ticket();
        ticketToSave.setTicketType("DAY");
        when(ticketRepository.save(ticketToSave.getUserId(),ticketToSave.getTicketType(),ticketToSave.getCreationDate()))
                .thenReturn(1);
        Ticket savedTicket = ticketService.createTicket(ticketToSave);
        assertEquals(ticketToSave,savedTicket);
    }

    @Test
    public void createTicket_NotExistedTicketType_NotSaved() {
        Ticket ticketToSave = new Ticket();
        ticketToSave.setTicketType("INVALID");
        when(ticketRepository.save(ticketToSave.getUserId(),ticketToSave.getTicketType(),ticketToSave.getCreationDate()))
                .thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () -> ticketService.createTicket(ticketToSave));
    }

    @Test
    public void getTicketById_IdOfExistedTicket_Found() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(1L);
        when(ticketRepository.findById(ticketToFound.getId())).thenReturn(Optional.of(ticketToFound));
        Ticket foundTicket = ticketService.getTicketById(1L);
        assertEquals(foundTicket,ticketToFound);
    }

    @Test
    public void getTicketById_IdOfNotExistedTicket_NotFound() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(1L);
        when(ticketRepository.findById(ticketToFound.getId())).thenThrow(TicketNotFoundException.class);
        assertThrows(TicketNotFoundException.class,() -> ticketService.getTicketById(1L));
    }

    @Test
    public void getTicketById_NegativeTicketId_NotFound() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(-1L);
        when(ticketRepository.findById(ticketToFound.getId())).thenThrow(TicketNotFoundException.class);
        assertThrows(TicketNotFoundException.class,() -> ticketService.getTicketById(-1L));
    }

    @Test
    public void getTicketById_ZeroTicketId_NotFound() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(0L);
        when(ticketRepository.findById(ticketToFound.getId())).thenThrow(TicketNotFoundException.class);
        assertThrows(TicketNotFoundException.class,() -> ticketService.getTicketById(0L));
    }

    @Test
    public void getTicketsByUserId_IdOfExistedUser_Found() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setUserId(1L);
        when(ticketRepository.findByUserId(ticketToFound.getUserId())).thenReturn(List.of(ticketToFound));
        List<Ticket> foundTickets = ticketService.getTicketsByUserId(1L);
        assertEquals(foundTickets,List.of(ticketToFound));
    }

    @Test
    public void getTicketsByUserId_IdOfNotExistedUser_NotFound() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setUserId(2L);
        when(ticketRepository.findByUserId(ticketToFound.getUserId())).thenReturn(Collections.emptyList());
        assertEquals(0,ticketService.getTicketsByUserId(2L).size());
    }

    @Test
    public void getTicketsByUserId_NegativeUserId_NotFound() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setUserId(-1L);
        when(ticketRepository.findByUserId(ticketToFound.getUserId())).thenReturn(Collections.emptyList());
        assertEquals(0,ticketService.getTicketsByUserId(-1L).size());
    }

    @Test
    public void getTicketsByUserId_NullUserId_NotFound() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setUserId(null);
        when(ticketRepository.findByUserId(ticketToFound.getUserId())).thenThrow(InvalidDataAccessApiUsageException.class);
        assertThrows(InvalidDataAccessApiUsageException.class,
                () -> ticketService.getTicketsByUserId(null));
    }

    @Test
    public void updateTicketType_ValidTicketTypeDAY_Updated() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(5L);
        ticketToUpdate.setTicketType("DAY");
        when(ticketRepository.findById(ticketToUpdate.getId()))
                .thenReturn(Optional.of(ticketToUpdate));
        when(ticketRepository.update(ticketToUpdate.getId(),ticketToUpdate.getUserId(),ticketToUpdate.getTicketType(),
                ticketToUpdate.getCreationDate()))
                .thenReturn(1);
        assertEquals(ticketToUpdate,
                ticketService.updateTicketType(ticketToUpdate.getId(),ticketToUpdate.getTicketType()));
    }

    @Test
    public void updateTicketType_ValidTicketTypeWEEK_Updated() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(2L);
        ticketToUpdate.setTicketType("WEEK");
        when(ticketRepository.findById(ticketToUpdate.getId()))
                .thenReturn(Optional.of(ticketToUpdate));
        when(ticketRepository.update(ticketToUpdate.getId(),ticketToUpdate.getUserId(),ticketToUpdate.getTicketType(),
                ticketToUpdate.getCreationDate()))
                .thenReturn(1);
        assertEquals(ticketToUpdate,
                ticketService.updateTicketType(ticketToUpdate.getId(),ticketToUpdate.getTicketType()));
    }

    @Test
    public void updateTicketType_ValidTicketTypeMONTH_Updated() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(3L);
        ticketToUpdate.setTicketType("MONTH");
        when(ticketRepository.findById(ticketToUpdate.getId()))
                .thenReturn(Optional.of(ticketToUpdate));
        when(ticketRepository.update(ticketToUpdate.getId(),ticketToUpdate.getUserId(),ticketToUpdate.getTicketType(),
                ticketToUpdate.getCreationDate()))
                .thenReturn(1);
        assertEquals(ticketToUpdate,
                ticketService.updateTicketType(ticketToUpdate.getId(),ticketToUpdate.getTicketType()));
    }

    @Test
    public void updateTicketType_ValidTicketTypeYEAR_Updated() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(4L);
        ticketToUpdate.setTicketType("YEAR");
        when(ticketRepository.findById(ticketToUpdate.getId()))
                .thenReturn(Optional.of(ticketToUpdate));
        when(ticketRepository.update(ticketToUpdate.getId(),ticketToUpdate.getUserId(),ticketToUpdate.getTicketType(),
                ticketToUpdate.getCreationDate()))
                .thenReturn(1);
        assertEquals(ticketToUpdate,
                ticketService.updateTicketType(ticketToUpdate.getId(),ticketToUpdate.getTicketType()));
    }

    @Test
    public void updateTicketType_InvalidTicketTypeINVALID_NotUpdated() {
        Ticket ticketToUpdate = new Ticket();
        ticketToUpdate.setId(1L);
        ticketToUpdate.setTicketType("INVALID");
        when(ticketRepository.findById(ticketToUpdate.getId()))
                .thenReturn(Optional.of(ticketToUpdate));
        when(ticketRepository.update(ticketToUpdate.getId(),ticketToUpdate.getUserId(),ticketToUpdate.getTicketType(),
                ticketToUpdate.getCreationDate()))
                .thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () ->
                ticketService.updateTicketType(ticketToUpdate.getId(),ticketToUpdate.getTicketType()));
    }

    @Test
    public void deleteTicket_IdOfExistedTicket_Deleted() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(21L);
        when(ticketRepository.findById(ticketToFound.getId())).thenReturn(Optional.of(ticketToFound));
        assertDoesNotThrow(() -> ticketService.deleteTicket(ticketToFound.getId()));
    }

    @Test
    public void deleteTicket_IdOfNotExistedTicket_NotDeleted() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(20L);
        when(ticketRepository.findById(ticketToFound.getId())).thenThrow(TicketNotFoundException.class);
        assertThrows(TicketNotFoundException.class,() -> ticketService.deleteTicket(ticketToFound.getId()));
    }

    @Test
    public void deleteTicket_NegativeIdOfNotExistedTicket_NotDeleted() {
        Ticket ticketToFound = new Ticket();
        ticketToFound.setId(-60L);
        when(ticketRepository.findById(ticketToFound.getId())).thenThrow(TicketNotFoundException.class);
        assertThrows(TicketNotFoundException.class,() -> ticketService.deleteTicket(ticketToFound.getId()));
    }

}
