package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_NAME = "purchases.txt";

    public static void savePurchasesToFile(Map<ProductTypes, List<Purchase>> map, double balance) {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            double totalSum = 0.0;
            double allSum = 0.0;
            for (ProductTypes type : ProductTypes.values()) {
                if (type == ProductTypes.ALL) {
                    continue;
                }
                writer.println(type.name() + ":");
                List<Purchase> list = map.get(type);
                if (list.isEmpty()) {
                    writer.println("Purchase list is empty!");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        Purchase purchase = list.get(i);
                        writer.println(purchase);
                        totalSum += purchase.getSumProduct();
                        if (i == list.size() - 1) {
                            writer.printf("Total sum of %s category: $%.2f%n", type.getName(), totalSum);
                            totalSum = 0.0;
                        }
                        allSum += purchase.getSumProduct();
                    }
                }
                writer.println();
            }
            writer.println("=====================================");
            writer.printf("Total sum: $%.2f%n", allSum);
            writer.printf("Balance: $%.2f%n", balance);
            System.out.println("\nPurchases were saved to file.");
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found.");
        }
    }

    public static void loadPurchasesFromFile(Map<ProductTypes, List<Purchase>> map) {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("Balance: $")) {
                    BudgetManager.balance = Double.parseDouble(line.substring(line.indexOf("$") + 1));
                    continue;
                }
                if (line.endsWith(":")) {
                    ProductTypes type = ProductTypes.valueOf(line.substring(0, line.length() - 1).trim());
                    while (fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine();
                        if (line.contains("Total sum") || line.endsWith("!")) {
                            break;
                        }
                        int lastIndex = line.lastIndexOf("$");
                        String name = line.substring(0, lastIndex - 1);
                        double price = Double.parseDouble(line.substring(lastIndex + 1));
                        Purchase purchase = new Purchase(name, price);
                        map.get(type).add(purchase);
                        map.get(ProductTypes.ALL).add(purchase);
                    }
                }
            }
            System.out.println("\nPurchases were loaded from file.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}