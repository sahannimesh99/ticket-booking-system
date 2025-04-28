package org.com.sahan.src.cli;


import org.com.sahan.src.manager.SimulationManager;
import org.com.sahan.src.service.TicketService;
import org.com.sahan.src.service.impl.TicketServiceQueueImpl;
import org.com.sahan.src.service.impl.TicketServiceSyncImpl;
import org.com.sahan.src.service.impl.TicketServiceLockImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class TicketSystemCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> serviceNames = new ArrayList<>();
        List<Function<Integer, TicketService>> serviceCreators = new ArrayList<>();

        serviceNames.add("Synchronized");
        serviceCreators.add(TicketServiceSyncImpl::new);

        serviceNames.add("ReentrantLock");
        serviceCreators.add(TicketServiceLockImpl::new);

        serviceNames.add("BlockingQueue");
        serviceCreators.add(TicketServiceQueueImpl::new);

        while (true) {
            System.out.println("\nChoose synchronization mechanism:");
            for (int i = 0; i < serviceNames.size(); i++) {
                System.out.println((i + 1) + ". " + serviceNames.get(i));
            }
            System.out.println((serviceNames.size() + 1) + ". Exit");

            int choice = scanner.nextInt();

            if (choice == serviceNames.size() + 1) {
                System.out.println("Exiting...");
                break;
            }

            if (choice < 1 || choice > serviceNames.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.print("Enter maximum ticket pool capacity: ");
            int capacity = scanner.nextInt();

            TicketService service = serviceCreators.get(choice - 1).apply(capacity);
            SimulationManager manager = new SimulationManager(service);
            manageThreads(scanner, manager);
        }

        scanner.close();
    }

    private static void manageThreads(Scanner scanner, SimulationManager manager) {
        while (true) {
            System.out.println("\nManage Threads:");
            System.out.println("1. Add Producer");
            System.out.println("2. Add Consumer");
            System.out.println("3. Add Reader");
            System.out.println("4. Add Writer");
            System.out.println("5. Show Ticket Status");
            System.out.println("6. Return to Main Menu");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    manager.addProducer();
                    break;
                case 2:
                    manager.addConsumer();
                    break;
                case 3:
                    manager.addReader();
                    break;
                case 4:
                    manager.addWriter();
                    break;
                case 5:
                    manager.showStatus();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid action. Try again.");
            }
        }
    }
}



