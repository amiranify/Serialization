import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String... args) throws Exception {
        String[] products = {"Хлеб", "Молоко", "Гречневая крупа", "Соль", "Сахар", "Растительное масло",
                "Сливочное масло", "Сыр", "Майонез", "Чеснок", "Бананы", "Яблоки", "Крабовые палочки",
                "Йогурт", "Пюре детское Тёма ", "Печенье Юбилейное", "Сок Добрый",
                "Кефир Весёлый молочник", "Колбаса Краковская", "Рис"};
        int[] prices = {33, 69, 80, 20, 65, 110, 150, 140, 86, 40, 59, 72, 79, 93, 75, 35, 86, 76, 230, 99};
        Basket basket = new Basket(products, prices);
        int productNum;
        int amount;

        File basketFile = new File("basket.txt");
        File log = new File("log.csv");
        File json = new File("basket.json");
        ClientLog clientLog = new ClientLog();

        Scanner scanner = new Scanner(System.in);

        if (basketFile.exists()) {
            System.out.println("Загрузить корзину");
            if (scanner.equals("")) {
                basket = Basket.loadFromTxtFile(basketFile);
                basket.printCart();
                System.out.println(" ");
            } else {
                basket = new Basket(products, prices);
            }
        }

        System.out.println("Ваша корзина: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб./шт.");
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }


            String[] split = input.split(" ");
            if (split.length != 2) {
                System.out.println("Ошибка ввода! Вы ввели одно или более двух чисел \n" +
                        "Ввод производится в формате двух чисел через пробел!");
                continue;
            }

            try {
                String a = split[0];
                productNum = Integer.parseInt(a) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода! Вы ввели не число! \n" +
                        "Ввод производится в формате двух чисел через пробел!");
                continue;
            }
            if (productNum + 1 > products.length || productNum < 0) {
                System.out.println("Ошибка ввода! Вы ввели слишком большое или отрицательное число! \n" +
                        "Ввод производится в формате двух чисел через пробел!");
                continue;
            }

            try {
                String b = split[1];
                amount = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода! Вы ввели не число! \n" +
                        "Ввод производится в формате двух чисел через пробел!");
                continue;
            }

            if (amount < 0) {
                System.out.println("Ошибка ввода! Вы ввели отрицательное число! \n" +
                        "Ввод производится в формате двух чисел через пробел!");
                continue;
            }

            basket.addToCart(productNum, amount);
            basket.saveToJSON(json);
            clientLog.log(productNum, amount);
            clientLog.exportAsCSV(log);
            basket.printCart();
        }
    }
}