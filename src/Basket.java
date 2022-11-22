import java.io.*;

public class Basket {


        protected String[] products;
        protected int[] price;
        protected int[] prodAmount;
        protected int[] sum;

        public Basket(String[] products, int[] price) {
            this.products = products;
            this.price = price;
            this.prodAmount = new int[products.length];
        }

        public Basket(String[] productName, int[] price, int[] prodAmount) {
            this.products = productName;
            this.price = price;
            this.prodAmount = prodAmount;
        }

        //Метод добавления в корзину продукта: количество и изменение цены в зависимости от добавленного количества
        public void addToCart(int productNum, int amount) {
            prodAmount[productNum] += amount;
            //sum[productNum] = price[productNum] * amount;
        }

        // Метод вывода на экран покупательской корзины.
        public void printCart(){
            System.out.println("Ваша корзина:");
            int sumProducts = 0;
            for (int i = 0; i < products.length; i++) {
                if (prodAmount[i] != 0) {
                    System.out.println(products[i] + " " + prodAmount[i] + " шт., " + price[i] + " руб.,итого: " + (prodAmount[i] * price[i]) + " рублей.");
                    sumProducts += prodAmount[i] * price[i];
                }
            }
            System.out.println("Общий счет: " + sumProducts + " рублей.");
        }

        //Метод сохранения корзины в текстовый файл
        public void saveTxt(File textFile) throws IOException {
            try (FileWriter out = new FileWriter(textFile);) {
                for (String st : products) {
                    out.write(st + ", ");
                }
                out.write("\n");
                for (int pr : price) {
                    out.write(pr + ", ");
                }
                out.write("\n");
                for (int amount : prodAmount) {
                    out.write(amount + ", ");
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        //Метод выгрузки из корзины файла
        public static Basket loadFromTxtFile(File textFile) {
            try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
                String productName = br.readLine(); //Массив названий
                String price = br.readLine(); //Массив цен
                String prodAmount = br.readLine(); //Массив количества

                String[] productStr = productName.split(", ");

                //Преобразование строки с ценами в int массив
                String[] priceStr = price.split(", ");
                int[] priceInt = new int[priceStr.length];
                for (int i = 0; i < priceInt.length; i++) {
                    priceInt[i] = Integer.parseInt(priceStr[i]);
                }

                //Преобразуем строку количества продуктов в int массив
                String[] amountStr = prodAmount.split(", ");
                int[] amountInt = new int[amountStr.length];
                for (int i = 0; i < amountInt.length; i++) {
                    amountInt[i] = Integer.parseInt(amountStr[i]);
                }

                return new Basket(productStr, priceInt, amountInt);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

