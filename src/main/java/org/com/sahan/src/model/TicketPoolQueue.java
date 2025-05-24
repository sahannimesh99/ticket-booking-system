package org.com.sahan.src.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPoolQueue {
    private final Queue<String> tickets;
    private final int maxCapacity;

    public TicketPoolQueue(int maxCapacity) {
        this.tickets = new LinkedList<>();
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

        tickets.offer(ticket);
        System.out.println("[Queue] Ticket added: " + ticket);
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

        String ticket = tickets.poll();
        System.out.println("[Queue] Ticket bought: " + ticket);
        notifyAll();
        return ticket;
    }

    public synchronized int getAvailableTickets() {
        return tickets.size();
    }
}
