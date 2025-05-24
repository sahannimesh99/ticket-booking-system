//package org.com.sahan.src.thread;
//
//import org.com.sahan.src.service.TicketService;
//
//public class Writer implements Runnable {
//    private final TicketService service;
//    private volatile boolean running = true;
//    private int writerCounter = 1000;
//    private int writeCount = 0;
//
//    public Writer(TicketService service) {
//        this.service = service;
//    }
//
//    public void stop() {
//        running = false;
//    }
//
//    @Override
//    public void run() {
//        int attempts = 0;
//        while (running && writeCount < 5 && attempts < 10) {
//            if (service.getAvailableTickets() < 5) {
//                System.out.println("[Writer] Added WriterTicket-" + writerCounter);
//                service.addTicket("WriterTicket-" + writerCounter++);
//                writeCount++;
//            } else {
//                System.out.println("[Writer] Pool full. Retry later...");
//            }
//
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//
//            attempts++;
//        }
//
//    }
//}

package org.com.sahan.src.thread;

import org.com.sahan.src.service.TicketService;

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
        StringBuilder output = new StringBuilder();
        int writeCount = 0;

        while (running && writeCount < 5) {
            String ticket = "WriterTicket-" + writerCounter++;
            System.out.println("[Writer] Attempting to add: " + ticket);
            service.addTicket(ticket);
            output.append(ticket).append(" ");
            writeCount++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("[Writer-" + Thread.currentThread().getId() + "] Tickets written: " + output.toString().trim());
    }

}


