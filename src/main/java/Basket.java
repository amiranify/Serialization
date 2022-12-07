import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Basket implements Serializable {
    protected String[] products;
    protected int[] price;
    protected int[] prodAmount;

    public Basket(String[] products, int[] price) {
        this.products = products;
        this.price = price;
        this.prodAmount = new int[products.length];
    }

    private Basket(String[] products, int[] price, int[] prodAmount) {
        this.products = products;
        this.price = price;
        this.prodAmount = prodAmount;
    }

    public void addToCart(int productNum, int amount) {
        prodAmount[productNum] += amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        int sumProducts = 0;
        for (int i = 0; i < products.length; i++) {
            if (prodAmount[i] != 0) {
                System.out.println(products[i] + "\t" + prodAmount[i] + " шт., " + price[i] + " руб.шт. итого: " + (prodAmount[i] * price[i]) + " рублей.");
                sumProducts += prodAmount[i] * price[i];
            }
        }
        System.out.println("Общий счет: " + sumProducts + " рублей.");
    }

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }

    public static Basket loadFromBin(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (Basket) in.readObject();
        }

    }

    public void saveToJSON(File textFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            writer.println(json);
        }
    }

    public static Basket loadFromJSON(File textFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(textFile))) {
            String json = scanner.nextLine();
            Gson gson = new Gson();
            FileReader reader = new FileReader(textFile);
            return gson.fromJson(reader, Basket.class);
        }
    }
}