package org.com.sahan.src.service.impl;


import org.com.sahan.src.model.TicketPoolLock;
import org.com.sahan.src.service.TicketService;

public class TicketServiceLockImpl implements TicketService {
    private final TicketPoolLock pool;

    public TicketServiceLockImpl(int capacity) {
        this.pool = new TicketPoolLock(capacity);
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
