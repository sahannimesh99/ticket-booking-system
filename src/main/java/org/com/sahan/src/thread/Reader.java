//package org.com.sahan.src.thread;
//
//import org.com.sahan.src.service.TicketService;
//
//public class Reader implements Runnable {
//    private final TicketService service;
//    private volatile boolean running = true;
//    private int readCount = 0;
//
//    public Reader(TicketService service) {
//        this.service = service;
//    }
//
//    public void stop() {
//        running = false;
//    }
//
//    @Override
//    public void run() {
//        while (running) {
//            int available = service.getAvailableTickets();
//            System.out.println("[Reader-" + Thread.currentThread().getId() + "] Available tickets: " + available);
//
//            readCount++;
//            if (readCount >= 5) {
//                stop();
//                break;
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.out.println("[Reader] Interrupted.");
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//}


package org.com.sahan.src.thread;

import org.com.sahan.src.service.TicketService;

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
        int readCount = 0;
        while (running && readCount < 5) {
            int available = service.getAvailableTickets();
            System.out.println("[Reader-" + Thread.currentThread().getId() + "] Read " + (readCount + 1) + ": Available tickets = " + available);
            readCount++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
