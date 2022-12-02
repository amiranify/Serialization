import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String[] products;
    protected int[] price;
    protected int[] prodAmount;

    public Basket(String[] products, int[] price) {
        this.products = products;
        this.price = price;
        this.prodAmount = new int[products.length];
    }

    public Basket(String[] products, int[] price, int[] prodAmount) {
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
                System.out.println(products[i] + " " + prodAmount[i] + " шт., " + price[i] + " руб.,итого: " + (prodAmount[i] * price[i]) + " рублей.");
                sumProducts += prodAmount[i] * price[i];
            }
        }
        System.out.println("Общий счет: " + sumProducts + " рублей.");
    }

    public void saveTxt(File textFile) throws RuntimeException {
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

    public static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String productName = br.readLine();
            String price = br.readLine();
            String prodAmount = br.readLine();

            String[] productStr = productName.split(", ");

            String[] priceStr = price.split(", ");
            int[] priceInt = new int[priceStr.length];
            for (int i = 0; i < priceInt.length; i++) {
                priceInt[i] = Integer.parseInt(priceStr[i]);
            }

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

    public void saveToJSON(File textFile) throws IOException {
        FileWriter writer = new FileWriter(textFile);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        writer.write(gson.toJson(this, Basket.class));
        writer.close();
    }

    public static Basket loadFromJSON(File textFile) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(textFile);
        return gson.fromJson(reader, Basket.class);
    }
}