package org.com.sahan.src.service.impl;


import org.com.sahan.src.model.TicketPoolSync;
import org.com.sahan.src.service.TicketService;

public class TicketServiceSyncImpl implements TicketService {
    private final TicketPoolSync pool;

    public TicketServiceSyncImpl(int capacity) {
        this.pool = new TicketPoolSync(capacity);
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

