package budget;

public enum Menu {

    MAIN_MENU {
        public void printMenu() {
            System.out.println("\nChoose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze (Sort)");
            System.out.println("0) Exit");
        }
    },

    SORT_PURCHASES {
        public void printMenu() {
            System.out.println("\nHow do you want to sort?");
            System.out.println("1) Sort all purchases");
            System.out.println("2) Sort by type");
            System.out.println("3) Sort certain type");
            System.out.println("4) Back");
        }
    },

    SORT_BY_TYPE {
        public void printMenu() {
            System.out.println("\nChoose the type of purchase");
            for (ProductTypes type : ProductTypes.values()) {
                if (!type.getName().equals("All")) {
                    System.out.println(type.ordinal() + 1 + ") " + type.getName());
                }
            }
        }
    },

    PRODUCTS_BOUGHT {
        public void printMenu() {
            System.out.println("\nChoose the type of purchase");
            for (ProductTypes type : ProductTypes.values()) {
                System.out.println(type.ordinal() + 1 + ") " + type.getName());
            }
            System.out.println(ProductTypes.values().length + 1 + ") Back");
        }
    },

    PRODUCTS_TO_BUY {
        public void printMenu() {
            System.out.println("\nChoose the type of purchase");
            for (ProductTypes type : ProductTypes.values()) {
                if (!type.getName().equals("All")) {
                    System.out.println(type.ordinal() + 1 + ") " + type.getName());
                }
            }
            System.out.println(ProductTypes.values().length + ") Back");
        }
    };

    public abstract void printMenu();
}