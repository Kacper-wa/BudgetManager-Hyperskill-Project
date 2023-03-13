package budget;

import java.util.*;

public class Sorter {

    private static final Scanner scanner = new Scanner(System.in);

    public static void sortAllPurchases(Map<ProductTypes, List<Purchase>> map) {
        List<Purchase> list = map.get(ProductTypes.ALL);
        double totalSum = 0.0;
        System.out.println("\nAll:");
        if (list.isEmpty()) {
            System.out.println("Purchase list is empty!");
        } else {
            Collections.sort(list, Comparator.comparingDouble(Purchase::getSumProduct).reversed());
            for (Purchase purchase : list) {
                System.out.println(purchase);
                totalSum += purchase.getSumProduct();
            }
            System.out.printf("Total sum: $%.2f%n", totalSum);
        }
    }

    public static void sortPurchasesByType(Map<ProductTypes, List<Purchase>> map) {
        double totalSum = 0.0;
        System.out.println("\nTypes:");
        List<ProductTypes> sortedTypes = Arrays.asList(ProductTypes.values());
        sortedTypes.sort((type1, type2) -> {
            double total1 = map.get(type1).stream().mapToDouble(Purchase::getSumProduct).sum();
            double total2 = map.get(type2).stream().mapToDouble(Purchase::getSumProduct).sum();
            return Double.compare(total2, total1);
        });

        for (ProductTypes type : sortedTypes) {
            if (type == ProductTypes.ALL) {
                continue;
            }
            List<Purchase> list = map.get(type);
            double totalSumOfType = 0.0;
            for (Purchase purchase : list) {
                totalSum += purchase.getSumProduct();
                totalSumOfType += purchase.getSumProduct();
            }
            System.out.printf("%s - $%.2f%n", type.getName(), totalSumOfType);
        }
        System.out.printf("Total sum: $%.2f%n", totalSum);
    }

    public static void sortPurchasesByCertainType(Map<ProductTypes, List<Purchase>> map) {
        Menu.SORT_BY_TYPE.printMenu();
        while (true) {
            int choice = scanner.nextInt();
            ProductTypes type = ProductTypes.values()[choice - 1];
            List<Purchase> list = map.get(type);
            double totalSum = 0.0;
            System.out.println("\n" + type.getName() + ":");
            if (list.isEmpty()) {
                System.out.println("Purchase list is empty!");
            } else {
                Collections.sort(list, Comparator.comparingDouble(Purchase::getSumProduct).reversed());
                for (Purchase purchase : list) {
                    System.out.println(purchase);
                    totalSum += purchase.getSumProduct();
                }
                System.out.printf("Total sum: $%.2f%n", totalSum);
            }
            return;
        }
    }
}

