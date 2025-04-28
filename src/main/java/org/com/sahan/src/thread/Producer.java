package org.com.sahan.src.thread;


import org.com.sahan.src.service.TicketService;

/**
 * Producer adds tickets into the ticket pool.
 */
public class Producer implements Runnable {
    private final TicketService service;
    private volatile boolean running = true;
    private int ticketCounter = 1;

    public Producer(TicketService service) {
        this.service = service;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            service.addTicket("Ticket-" + ticketCounter++);
            try {
                Thread.sleep(500); // Produce a ticket every 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println("[Producer] Interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

