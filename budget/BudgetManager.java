package budget;

import java.util.*;

public class BudgetManager {
    private static final Scanner scanner = new Scanner(System.in);
    protected static double balance;
    protected final Map<ProductTypes, List<Purchase>> map;

    public BudgetManager() {
        balance = 0.0;
        map = new HashMap<>();
        for (ProductTypes type : ProductTypes.values()) {
            map.put(type, new ArrayList<>());
        }
    }

    public void run() {
        while (true) {
            Menu.MAIN_MENU.printMenu();
            switch (scanner.nextInt()) {
                case 1 -> addIncome();
                case 2 -> chooseProductType();
                case 3 -> showPurchaseList();
                case 4 -> getBalance();
                case 5 -> savePurchasesToFile();
                case 6 -> loadPurchasesFromFile();
                case 7 -> sortPurchases();
                case 0 -> exitProgram();
            }
        }
    }

    public void addIncome() {
        System.out.println("\nEnter income:");
        balance += scanner.nextDouble();
        System.out.println("Income was added!");
    }

    public void chooseProductType() {
        while (true) {
            Menu.PRODUCTS_TO_BUY.printMenu();
            int choice = scanner.nextInt();
            if (choice >= ProductTypes.values().length) {
                return;
            }
            try {
                ProductTypes type = ProductTypes.values()[choice - 1];
                addPurchase(type);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    public void addPurchase(ProductTypes type) {
        scanner.nextLine();
        System.out.println("\nEnter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = scanner.nextDouble();
        if (price > balance) {
            System.out.println("Not enough money!");
            return;
        }
        Purchase purchase = new Purchase(name, price);
        map.get(type).add(purchase);
        map.get(ProductTypes.ALL).add(purchase);
        balance -= price;
        System.out.println("Purchase was added!");

    }

    public void sortPurchases() {
        while (true) {
            Menu.SORT_PURCHASES.printMenu();
            int choice = scanner.nextInt();
            if (choice == 4) {
                return;
            }
            switch (choice) {
                case 1 -> Sorter.sortAllPurchases(map);
                case 2 -> Sorter.sortPurchasesByType(map);
                case 3 -> Sorter.sortPurchasesByCertainType(map);
            }
        }
    }

    public void showPurchaseList() {
        while (true) {
            Menu.PRODUCTS_BOUGHT.printMenu();
            int choice = scanner.nextInt();
            if (choice == ProductTypes.values().length + 1) {
                return;
            }
            try {
                ProductTypes type = ProductTypes.values()[choice - 1];
                System.out.println("\n" + type.name() + ":");
                showPurchases(type);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }
    }

    public void showPurchases(ProductTypes type) {
        List<Purchase> list = map.get(type);
        if (list.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            double totalSum = 0.0;
            for (Purchase purchase : list) {
                System.out.println(purchase);
                totalSum += purchase.getSumProduct();
            }
            System.out.printf("Total sum: $%.2f%n", totalSum);
        }
    }

    public void getBalance() {
        System.out.printf("%nBalance: $%.2f%n", balance);
    }

    public void savePurchasesToFile() {
        FileManager.savePurchasesToFile(map, balance);
    }

    public void loadPurchasesFromFile() {
        FileManager.loadPurchasesFromFile(map);
    }

    public void exitProgram() {
        System.out.println("\nBye!");
        System.exit(0);
    }
}