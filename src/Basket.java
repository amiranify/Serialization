import java.io.*;

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

    public void saveBin(File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Basket loadFromBinFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        return (Basket) ois.readObject();
    }
}