package org.example;

/**
 * @author Sahan Nimesha
 */

public class Reader implements Runnable {
    private final TicketPool pool;

    public Reader(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        System.out.println("Available tickets: " + pool.getAvailableTickets());
    }
}
