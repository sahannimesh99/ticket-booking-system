package org.example;

/**
 * @author Sahan Nimesha
 */

public class Producer implements Runnable {
    private final TicketPool pool;
    private final int id;
    private final int maxTickets;

    public Producer(TicketPool pool, int id, int maxTickets) {
        this.pool = pool;
        this.id = id;
        this.maxTickets = maxTickets;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxTickets; i++) {
            if (pool.addTicket(i)) {
                System.out.println("Producer " + id + " added ticket " + i);
            }
        }
    }
}
