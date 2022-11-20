import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] products = {"Хлеб", "Молоко", "Гречневая крупа", "Соль", "Сахар", "Растительное масло",
                "Сливочное масло", "Сыр", "Майонез", "Чеснок", "Бананы", "Яблоки", "Крабовые палочки",
                "Йогурт", "Пюре детское Фруто-няня", "Печенье Юбилейное", "Сок Добрый",
                "Кефир Весёлый молочник", "Колбаса Краковская", "Рис"};
        int[] prices = {33, 69, 80, 20, 65, 110, 150, 140, 86, 40, 59, 72, 79, 93, 75, 35, 86, 76, 230, 99};
        Basket basket = new Basket(products, prices);
        int[] sum = new int[prices.length];
        int[] count = new int[products.length];

        int productNumber = 0;
        int productCount = 0;
        int sumProducts = 0;


        File basketFile = new File("basket.txt");
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


        System.out.println("Список возможных товаров для покупки: ");
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
                System.out.println("Ошибка ввода: Вы ввели 1 число или более 2 чисел ");
                continue;
            }


            // Исключение для ввода слов NumberFormatException
            try {
                String a = split[0];//до пробела, чтобы получить номер продукта
                productNumber = Integer.parseInt(a) - 1;
                // throw new NumberFormatException("Ошибка ввода: Вы ввели не число. Для корректной работы программы введите число!");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: Вы ввели не число. Для корректной работы программы введите число!");
                continue;
            }
            if (productNumber + 1 > products.length || productNumber < 0) {
                System.out.println("Ошибка ввода: Вы ввели слишком большое или неположительное число!");
                continue;
            }


            String b = split[1]; //после пробела, чтобы получить количество
            try {

                productCount = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода: Вы ввели не число. Для корректной работы программы введите число!");
                continue;
            }

            if (productCount < 0) {
                System.out.println("Ошибка ввода: Вы ввели слишком большое или неположительное число!");
                continue;
            }

            basket.addToCart(productNumber, productCount);
            basket.saveTxt(basketFile);
            basket.printCart();
        }
        // basket.printCart();
    }
}
