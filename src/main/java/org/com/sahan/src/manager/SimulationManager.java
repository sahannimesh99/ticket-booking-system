package org.com.sahan.src.manager;


import org.com.sahan.src.service.TicketService;
import org.com.sahan.src.thread.Consumer;
import org.com.sahan.src.thread.Producer;
import org.com.sahan.src.thread.Reader;
import org.com.sahan.src.thread.Writer;

import java.util.ArrayList;
import java.util.List;

public class SimulationManager {
    private final TicketService service;
    private final List<Thread> producers = new ArrayList<>();
    private final List<Thread> consumers = new ArrayList<>();
    private final List<Thread> readers = new ArrayList<>();
    private final List<Thread> writers = new ArrayList<>();

    public SimulationManager(TicketService service) {
        this.service = service;
    }

    public void addProducer() {
        Producer producer = new Producer(service);
        Thread thread = new Thread(producer);
        producers.add(thread);
        thread.start();
    }

    public void addConsumer() {
        Consumer consumer = new Consumer(service);
        Thread thread = new Thread(consumer);
        consumers.add(thread);
        thread.start();
    }

    public void addReader() {
        Reader reader = new Reader(service);
        Thread thread = new Thread(reader);
        readers.add(thread);
        thread.start();
    }

    public void addWriter() {
        Writer writer = new Writer(service);
        Thread thread = new Thread(writer);
        writers.add(thread);
        thread.start();
    }

    public void showStatus() {
        System.out.println("[SimulationManager] Current available tickets: " + service.getAvailableTickets());
    }
}

