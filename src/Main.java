import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] products = {"Хлеб", "Молоко", "Гречка"};
        int[] prices = {30, 85, 70};
        int[] cart = new int[products.length];
        int sumProducts = 0;
        Scanner scanner = new Scanner(System.in);

        printProducts(products, prices);

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`.");

            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("Не верный ввод!");
                continue;
            }

            int productNumber;
            int productCount;

            try {
                productNumber = Integer.parseInt(parts[0]) - 1;
                productCount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Не верный ввод: " + e.getMessage());
                continue;
            }

            if (productNumber < 0 || productNumber > products.length - 1) {
                System.out.println("Не верный номер продукта!");
                continue;
            }

            if (productCount <= 0) {
                System.out.println("Не верное количество!");
                continue;
            }

            int currentPrice = prices[productNumber];

            sumProducts += currentPrice * productCount;
            cart[productNumber] += productCount;
        }

        printCart(products, prices, cart, sumProducts);
    }

    public static void printProducts(String[] products, int[] prices) {
        System.out.println("Список возможных товаров для покупки:");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
    }

    public static void printCart(String[] products, int[] prices, int[] cart, int sumProducts) {
        System.out.println("Ваша корзина:");

        for (int i = 0; i < products.length; i++) {
            if (cart[i] > 0) {
                System.out.println(products[i] + " " + cart[i] + " шт " + prices[i] + " руб/шт " + (prices[i] * cart[i]) + " руб в сумме");
            }
        }

        System.out.println("Итого: " + sumProducts + " руб");
    }
}