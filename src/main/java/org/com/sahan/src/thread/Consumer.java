//package org.com.sahan.src.thread;
//
//import org.com.sahan.src.service.TicketService;
//
//// Consumer buys tickets from the ticket pool.
//
//public class Consumer implements Runnable {
//    private final TicketService service;
//    private volatile boolean running = true;
//    private int consumedCount = 0;
//    public Consumer(TicketService service) {
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
//            service.buyTicket();
//            consumedCount++;
//
//            if (consumedCount >= 5) {
//                stop();
//                break;
//            }
//        }
//    }
//}

package org.com.sahan.src.thread;

import org.com.sahan.src.service.TicketService;

// Consumer buys tickets from the ticket pool.
public class Consumer implements Runnable {
    private final TicketService service;
    private volatile boolean running = true;
    private int consumedCount = 0;

    public Consumer(TicketService service) {
        this.service = service;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {

            try {
                service.buyTicket();
                consumedCount++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            if (consumedCount >= 5) {
                System.out.println("[Consumer-" + Thread.currentThread().getId() + "] ");
                stop();
                break;
            }
        }
    }
}
