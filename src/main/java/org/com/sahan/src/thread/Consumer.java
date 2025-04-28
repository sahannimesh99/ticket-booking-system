package org.com.sahan.src.thread;

import org.com.sahan.src.service.TicketService;
/**
 * Consumer buys tickets from the ticket pool.
 */
public class Consumer implements Runnable {
    private final TicketService service;
    private volatile boolean running = true;

    public Consumer(TicketService service) {
        this.service = service;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            service.buyTicket();
            try {
                Thread.sleep(700); // Try to buy a ticket every 0.7 seconds
            } catch (InterruptedException e) {
                System.out.println("[Consumer] Interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

