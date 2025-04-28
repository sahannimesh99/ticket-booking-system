package org.com.sahan.src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TicketPoolSync uses intrinsic locks (synchronized) to protect shared resources.
 */
public class TicketPoolSync {
    private final List<String> tickets;
    private final int maxCapacity;

    public TicketPoolSync(int maxCapacity) {
        this.tickets = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(String ticket) {
        if (tickets.size() < maxCapacity) {
            tickets.add(ticket);
            System.out.println("[Sync] Ticket added: " + ticket);
        } else {
            System.out.println("[Sync] Ticket pool full. Cannot add ticket: " + ticket);
        }
    }

    public synchronized String buyTicket() {
        if (!tickets.isEmpty()) {
            String ticket = tickets.remove(0);
            System.out.println("[Sync] Ticket bought: " + ticket);
            return ticket;
        } else {
            System.out.println("[Sync] No tickets available to buy.");
            return null;
        }
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }

    public synchronized List<String> getSnapshot() {
        return new ArrayList<>(tickets);
    }
}
