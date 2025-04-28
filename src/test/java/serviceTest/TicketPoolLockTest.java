package serviceTest;


import org.com.sahan.src.model.TicketPoolLock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketPoolLockTest {

    private TicketPoolLock ticketPool;

    @BeforeEach
    void setUp() {
        ticketPool = new TicketPoolLock(2);
    }

    @Test
    void testAddTicket() {
        ticketPool.addTicket("Ticket-1");
        assertEquals(1, ticketPool.getAvailableTickets());
    }

    @Test
    void testBuyTicket() {
        ticketPool.addTicket("Ticket-1");
        String ticket = ticketPool.buyTicket();
        assertEquals("Ticket-1", ticket);
        assertEquals(0, ticketPool.getAvailableTickets());
    }

    @Test
    void testFullPool() {
        ticketPool.addTicket("Ticket-1");
        ticketPool.addTicket("Ticket-2");
        ticketPool.addTicket("Ticket-3"); // Should be rejected due to exceeding limit
        assertEquals(2, ticketPool.getAvailableTickets());
    }

    @Test
    void testEmptyPool() {
        String ticket = ticketPool.buyTicket();
        assertNull(ticket);
    }

    @Test
    void testConcurrentAccess() throws InterruptedException {
        ticketPool = new TicketPoolLock(100);

        Thread producer1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticketPool.addTicket("P1-Ticket-" + i);
            }
        });

        Thread producer2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticketPool.addTicket("P2-Ticket-" + i);
            }
        });

        producer1.start();
        producer2.start();

        producer1.join();
        producer2.join();

        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticketPool.buyTicket();
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticketPool.buyTicket();
            }
        });

        consumer1.start();
        consumer2.start();

        consumer1.join();
        consumer2.join();

        assertEquals(0, ticketPool.getAvailableTickets());
    }

}
