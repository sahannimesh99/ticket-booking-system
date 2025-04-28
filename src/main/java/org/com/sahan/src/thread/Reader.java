package org.com.sahan.src.thread;


import org.com.sahan.src.service.TicketService;

//Reader checks how many tickets are available in the pool.

public class Reader implements Runnable {
    private final TicketService service;
    private volatile boolean running = true;

    public Reader(TicketService service) {
        this.service = service;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            int available = service.getAvailableTickets();
            System.out.println("[Reader] Available tickets: " + available);
            try {
                Thread.sleep(1000); // Read every 1 second
            } catch (InterruptedException e) {
                System.out.println("[Reader] Interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

