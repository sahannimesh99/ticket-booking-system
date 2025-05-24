package org.com.sahan.src.model;

import java.util.ArrayList;
import java.util.List;

public class TicketPoolSync {
    private final List<String> tickets;
    private final int maxCapacity;

    public TicketPoolSync(int maxCapacity) {
        this.tickets = new ArrayList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(String ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        tickets.add(ticket);
        System.out.println("[Sync] Ticket added: " + ticket);
        notifyAll();
    }

    public synchronized String buyTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        String ticket = tickets.remove(0);
        System.out.println("[Sync] Ticket bought: " + ticket);
        notifyAll();
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }
}
