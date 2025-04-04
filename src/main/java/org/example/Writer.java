package org.example;

/**
 * @author Sahan Nimesha
 */

public class Writer implements Runnable {
    private final TicketPool pool;
    private final int id;
    private final int newTickets;

    public Writer(TicketPool pool, int id, int newTickets) {
        this.pool = pool;
        this.id = id;
        this.newTickets = newTickets;
    }

    @Override
    public void run() {
        for (int i = 0; i < newTickets; i++) {
            if (pool.addTicket(i)) {
                System.out.println("Writer " + id + " added ticket " + i);
            }
        }
    }
}
