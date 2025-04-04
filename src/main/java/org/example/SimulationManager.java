package org.example;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sahan Nimesha
 */

public class SimulationManager {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial ticket pool size: ");
        int size = scanner.nextInt();
        TicketPool ticketPool = new TicketPool(size);

        while (true) {
            System.out.println("\n--- Ticket System Menu ---");
            System.out.println("1. Add Producer");
            System.out.println("2. Add Consumer");
            System.out.println("3. Add Reader");
            System.out.println("4. Add Writer");
            System.out.println("5. Check Available Tickets");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Producer ID: ");
                    int producerId = scanner.nextInt();
                    System.out.print("Enter number of tickets to produce: ");
                    int prodTickets = scanner.nextInt();
                    executor.execute(new Producer(ticketPool, producerId, prodTickets));
                }
                case 2 -> {
                    System.out.print("Enter Consumer ID: ");
                    int consumerId = scanner.nextInt();
                    executor.execute(new Consumer(ticketPool, consumerId));
                }
                case 3 -> executor.execute(new Reader(ticketPool));
                case 4 -> {
                    System.out.print("Enter Writer ID: ");
                    int writerId = scanner.nextInt();
                    System.out.print("Enter number of new tickets to add: ");
                    int newTickets = scanner.nextInt();
                    executor.execute(new Writer(ticketPool, writerId, newTickets));
                }
                case 5 -> System.out.println("Current tickets available: " + ticketPool.getAvailableTickets());
                case 6 -> {
                    executor.shutdown();
                    scanner.close();
                    System.out.println("System exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
