package org.com.sahan.src.service;

public interface TicketService {
    void addTicket(String ticket);

    String buyTicket();

    int getAvailableTickets();
}
