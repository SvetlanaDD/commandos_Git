import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] products = {"Хлеб", "Молоко", "Гречка"};
        int[] prices = {30, 85, 70};
        int[] cart = new int[products.length];
        Scanner scanner = new Scanner(System.in);

        printProducts(products, prices);

        while (true) {
            System.out.println("Выберите товар и количество (для уменьшения товара - отрицательные числа, " +
                    "для обнуления - 0) или введите `end`.");

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

            if (productCount < 0) {
                if ((cart[productNumber] + productCount) < 0) {
                    System.out.println("В корзине всего " + cart[productNumber] + " шт. товара `" +
                            products[productNumber] + "`, хотите удалить товар из корзины - введите 0.");
                    continue;
                }
            }
            if (productCount == 0) {
                cart[productNumber] = 0;
            }

            cart[productNumber] += productCount;
        }

        printCart(products, prices, cart);
    }

    public static void printProducts(String[] products, int[] prices) {
        System.out.println("Список возможных товаров для покупки:");

        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
    }

    public static void printCart(String[] products, int[] prices, int[] cart) {
        int sumProducts = 0;
        System.out.println("Ваша корзина:");

        for (int i = 0; i < products.length; i++) {
            if (cart[i] > 0) {
                System.out.println(products[i] + " " + cart[i] + " шт " + prices[i] + " руб/шт " + (prices[i] * cart[i]) + " руб в сумме");
                sumProducts = sumProducts + (prices[i] * cart[i]);
            }
        }

        System.out.println("Итого: " + sumProducts + " руб");
    }
}