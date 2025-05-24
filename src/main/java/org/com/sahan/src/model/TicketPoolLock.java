package org.com.sahan.src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TicketPoolLock {
    private final List<String> tickets;
    private final int maxCapacity;
    private final ReentrantReadWriteLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public TicketPoolLock(int maxCapacity) {
        this.tickets = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.lock = new ReentrantReadWriteLock();
        this.notFull = lock.writeLock().newCondition();
        this.notEmpty = lock.writeLock().newCondition();
    }

    public void addTicket(String ticket) {
        lock.writeLock().lock();
        try {
            while (tickets.size() >= maxCapacity) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            tickets.add(ticket);
            System.out.println("[Lock] Ticket added: " + ticket);
            notEmpty.signalAll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String buyTicket() {
        lock.writeLock().lock();
        try {
            while (tickets.isEmpty()) {
                try {
                    System.out.println("[Lock] Waiting for tickets...");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }

            String ticket = tickets.remove(0);
            System.out.println("[Lock] Ticket bought: " + ticket);
            notFull.signalAll();
            return ticket;
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
