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

    private final List<Producer> producerTasks = new ArrayList<>();
    private final List<Thread> producerThreads = new ArrayList<>();

    private final List<Consumer> consumerTasks = new ArrayList<>();
    private final List<Thread> consumerThreads = new ArrayList<>();

    private final List<Reader> readerTasks = new ArrayList<>();
    private final List<Thread> readerThreads = new ArrayList<>();

    private final List<Writer> writerTasks = new ArrayList<>();
    private final List<Thread> writerThreads = new ArrayList<>();

    public SimulationManager(TicketService service) {
        this.service = service;
    }

    public void addProducer() {
        Producer producer = new Producer(service);
        Thread thread = new Thread(producer);
        producerTasks.add(producer);
        producerThreads.add(thread);
        thread.start();
    }

    public void addConsumer() {
        Consumer consumer = new Consumer(service);
        Thread thread = new Thread(consumer);
        consumerTasks.add(consumer);
        consumerThreads.add(thread);
        thread.start();
    }

    public void addReader() {
        Reader reader = new Reader(service);
        Thread thread = new Thread(reader);
        readerTasks.add(reader);
        readerThreads.add(thread);
        thread.start();
    }

    public void addWriter() {
        Writer writer = new Writer(service);
        Thread thread = new Thread(writer);
        writerTasks.add(writer);
        writerThreads.add(thread);
        thread.start();
    }

    public void showStatus() {
        System.out.println("[SimulationManager] Current available tickets: " + service.getAvailableTickets());
    }

    public void stopAllThreads() {
        System.out.println("[SimulationManager] Stopping all threads...");

        for (Producer p : producerTasks) p.stop();
        for (Consumer c : consumerTasks) c.stop();
        for (Reader r : readerTasks) r.stop();
        for (Writer w : writerTasks) w.stop();

        for (Thread t : producerThreads) t.interrupt();
        for (Thread t : consumerThreads) t.interrupt();
        for (Thread t : readerThreads) t.interrupt();
        for (Thread t : writerThreads) t.interrupt();

        System.out.println("[SimulationManager] All threads have been signaled to stop.");
    }
}
