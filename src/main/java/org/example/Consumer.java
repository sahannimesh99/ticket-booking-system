package org.example;

/**
 * @author Sahan Nimesha
 */

public class Consumer implements Runnable {

    private final TicketPool pool;
    private final int id;

    public Consumer(TicketPool pool, int id) {
        this.pool = pool;
        this.id = id;
    }

    @Override
    public void run() {
        Integer ticket;
        while ((ticket = pool.buyTicket()) != null) {
            System.out.println("Consumer " + id + " bought ticket " + ticket);
        }
    }
}
