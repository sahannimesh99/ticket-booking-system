package org.com.sahan.src.thread;


import org.com.sahan.src.service.TicketService;


// Writer adds tickets with a different ticket range to simulate separate activity.
public class Writer implements Runnable {
    private final TicketService service;
    private volatile boolean running = true;
    private int writerCounter = 1000;

    public Writer(TicketService service) {
        this.service = service;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            service.addTicket("WriterTicket-" + writerCounter++);
            try {
                Thread.sleep(1500); // Write every 1.5 seconds
            } catch (InterruptedException e) {
                System.out.println("[Writer] Interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}

