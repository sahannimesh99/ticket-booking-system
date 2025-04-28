package org.com.sahan.src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * TicketPoolLock uses explicit ReentrantReadWriteLock for finer control.
 */
public class TicketPoolLock {
    private final List<String> tickets;
    private final int maxCapacity;
    private final ReentrantReadWriteLock lock;

    public TicketPoolLock(int maxCapacity) {
        this.tickets = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.lock = new ReentrantReadWriteLock();
    }

    public void addTicket(String ticket) {
        lock.writeLock().lock();
        try {
            if (tickets.size() < maxCapacity) {
                tickets.add(ticket);
                System.out.println("[Lock] Ticket added: " + ticket);
            } else {
                System.out.println("[Lock] Ticket pool full. Cannot add ticket: " + ticket);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String buyTicket() {
        lock.writeLock().lock();
        try {
            if (!tickets.isEmpty()) {
                String ticket = tickets.remove(0);
                System.out.println("[Lock] Ticket bought: " + ticket);
                return ticket;
            } else {
                System.out.println("[Lock] No tickets available to buy.");
                return null;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getAvailableTickets() {
        lock.readLock().lock();
        try {
            return tickets.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<String> getSnapshot() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(tickets);
        } finally {
            lock.readLock().unlock();
        }
    }
}
