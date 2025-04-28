package org.com.sahan.src.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TicketPoolQueue simulates a simple thread-safe queue behavior manually.
 */
public class TicketPoolQueue {
    private final Queue<String> tickets;
    private final int maxCapacity;

    public TicketPoolQueue(int maxCapacity) {
        this.tickets = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(String ticket) {
        if (tickets.size() < maxCapacity) {
            tickets.offer(ticket);
            System.out.println("[Queue] Ticket added: " + ticket);
        } else {
            System.out.println("[Queue] Ticket pool full. Cannot add ticket: " + ticket);
        }
    }

    public synchronized String buyTicket() {
        String ticket = tickets.poll();
        if (ticket != null) {
            System.out.println("[Queue] Ticket bought: " + ticket);
        } else {
            System.out.println("[Queue] No tickets available to buy.");
        }
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }
}
