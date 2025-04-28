package org.com.sahan.src.service.impl;


import org.com.sahan.src.model.TicketPoolQueue;
import org.com.sahan.src.service.TicketService;

/**
 * Service implementation using simple queue (manual thread safety).
 */
public class TicketServiceQueueImpl implements TicketService {
    private final TicketPoolQueue pool;

    public TicketServiceQueueImpl(int capacity) {
        this.pool = new TicketPoolQueue(capacity);
    }

    @Override
    public void addTicket(String ticket) {
        pool.addTicket(ticket);
    }

    @Override
    public String buyTicket() {
        return pool.buyTicket();
    }

    @Override
    public int getAvailableTickets() {
        return pool.getAvailableTickets();
    }
}
