package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Sahan Nimesha
 */

public class TicketPool {
    private final BlockingQueue<Integer> tickets;

    public TicketPool(int size) {
        this.tickets = new LinkedBlockingQueue<>(size);
    }

    public synchronized boolean addTicket(int ticket) {
        return tickets.offer(ticket);
    }

    public synchronized Integer buyTicket() {
        return tickets.poll();
    }

    public int getAvailableTickets() {
        return tickets.size();
    }
}
