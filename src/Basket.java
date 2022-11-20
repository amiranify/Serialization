import java.io.*;

public class Basket {
//    protected String[] productName;
//    protected int[] price;
//
//    public Basket(String[] productName, int[] price) {
//        this.productName = productName;
//        this.price = price;
//    }
//
//    public void addToCart(int productNum, int amount) {
//
//    }
//
//    public void printCart() {
//        System.out.println("Ваша корзина:");
//        int sumProducts = 0;
//        for (int i = 0; i < productName.length; i++) {
//
//        }
//        public void saveTxt(File textFile) throws IOException {
//            try (PrintWriter out = new PrintWriter(file);) {
//        ...
//                for (long e : longArrInField)
//                    out.print(e + " ");
//            }
//        ...
//        }
//    }
//        public static Basket loadFromTxtFile(File textFile){
//            return new Basket();
//        }
//
//
//    public void saveTxt(File basketFile) {
//    }
//}

        protected String[] productName;
        protected int[] price;
        protected int[] prodAmount;
        protected int[] sum;

        public Basket(String[] productName, int[] price) {
            this.productName = productName;
            this.price = price;
            this.prodAmount = new int[productName.length];
        }

        public Basket(String[] productName, int[] price, int[] prodAmount) {
            this.productName = productName;
            this.price = price;
            this.prodAmount = prodAmount;
        }

        //Метод добавления в корзину продукта: количество и изменение цены в зависимости от добавленного количества
        public void addToCart(int productNum, int amount) {
            prodAmount[productNum] += amount;
            //sum[productNum] = price[productNum] * amount;
        }

        // Метод вывода на экран покупательской корзины.
        public void printCart() {
            System.out.println(" ");
            System.out.println("Ваша корзина:");
            int sumProducts = 0;
            for (int i = 0; i < productName.length; i++) {
                if (prodAmount[i] != 0) {
                    System.out.println(productName[i] + " " + prodAmount[i] + " шт., " + price[i] + " руб., " + (prodAmount[i] * price[i]) + " рублей в сумме.");
                    sumProducts += prodAmount[i] * price[i];
                }
            }
            System.out.println("Итого в корзине: " + sumProducts + " рублей.");
        }

        //Метод сохранения корзины в текстовый файл
        public void saveTxt(File textFile) throws IOException {
            try (FileWriter out = new FileWriter(textFile);) {
                for (String st : productName) {
                    out.write(st + "@");
                }
                out.write("\n");
                for (int pr : price) {
                    out.write(pr + "@");
                }
                out.write("\n");
                for (int amount : prodAmount) {
                    out.write(amount + "@");
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        //Метод выгрузки из карзины файла
        public static Basket loadFromTxtFile(File textFile) {
            try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
                String productName = br.readLine(); //Массив названий
                String price = br.readLine(); //Массив цен
                String prodAmount = br.readLine(); //Массив количества

                String[] productStr = productName.split("@");

                //Преобразование строки с ценами в интовый массив
                String[] priceStr = price.split("@");
                int[] priceInt = new int[priceStr.length];
                for (int i = 0; i < priceInt.length; i++) {
                    priceInt[i] = Integer.parseInt(priceStr[i]);
                }

                //Преобразуем строку количества продуктов в интовый массив
                String[] amountStr = prodAmount.split("@");
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

